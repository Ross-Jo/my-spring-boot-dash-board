package springboot.domain.posts;

import org.assertj.core.api.LocalDateTimeAssert;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

@SpringBootTest
public class PostsRepositoryTests {

    @Autowired
    PostsRepository postsRepository;

    @AfterEach
    public void cleanup() {
        postsRepository.deleteAll();
    }

    @Test
    public void fetch_posts() {
        Posts mockPost = Posts.builder().title("title").author("author").content("content").build();
        postsRepository.save(mockPost);

        List<Posts> postsList = postsRepository.findAll();

        assertThat(postsList.get(0)).isEqualToComparingFieldByField(mockPost);
    }

    @Test
    public void baseTimeEntityRegister() {
        LocalDateTime now = LocalDateTime.now();
        Posts mockPost = Posts.builder().title("title").author("author").content("content").build();
        postsRepository.save(mockPost);

        List<Posts> postsList = postsRepository.findAll();

        Posts posts = postsList.get(0);
        assertThat(posts.getCreatedDate()).isAfter(now);
        assertThat(posts.getModifiedDate()).isAfter(now);
    }

}
