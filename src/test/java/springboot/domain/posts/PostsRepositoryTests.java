package springboot.domain.posts;

import org.assertj.core.api.LocalDateTimeAssert;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
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

        assertThat(postsList.get(0).getTitle()).isEqualTo("title");
        assertThat(postsList.get(0).getAuthor()).isEqualTo("author");
        assertThat(postsList.get(0).getContent()).isEqualTo("content");
    }

    @Test
    public void baseTimeEntityRegister() {
        LocalDateTime now = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
//      now.truncatedTo(ChronoUnit.SECONDS).format(DateTimeFormatter.ISO_DATE_TIME));

        Posts mockPost = Posts.builder().title("title").author("author").content("content").build();
        postsRepository.save(mockPost);

        List<Posts> postsList = postsRepository.findAll();

        Posts posts = postsList.get(0);

        assertThat(posts.getCreatedDate()).isAfterOrEqualTo(now);
        assertThat(posts.getModifiedDate()).isAfterOrEqualTo(now);
    }

}
