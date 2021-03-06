package com.zhenxi.book.springboot.domain.posts;


import com.zhenxi.book.springboot.domain.Posts;
import com.zhenxi.book.springboot.domain.PostsRespository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostsRepositoryTest {

    @Autowired
    PostsRespository postsRespository;

    @After
    public void cleanUp() {
        postsRespository.deleteAll();
    }

    @Test
    public void 게시글저장_불러오기() {
        //given
        String title = "테스트 게시글";
        String content = "테스트 본문";

        postsRespository.save(Posts.builder()
                .author("zhenxiKim@gmail.com")
                .content(content)
                .title(title)
                .build());

        //when
        List<Posts> postsList = postsRespository.findAll();

        //then
        Posts posts = postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);

    }

    @Test
    public void BaseTimeEntity_등록(){

        //given
        LocalDateTime now = LocalDateTime.of(2020,2,23,0,0,0);
        postsRespository.save(Posts.builder()
                        .title("title")
                        .content("content")
                        .author("author")
                        .build());

        //when
        List<Posts> postsList = postsRespository.findAll();

        //then
        Posts posts = postsList.get(0);
        System.out.println(">>>>> createDate="+posts.getCreatedTime() +", modifiedDate="+posts.getModifiedDate());

        assertThat(posts.getCreatedTime()).isAfter(now);
        assertThat(posts.getModifiedDate()).isAfter(now);


    }
}
