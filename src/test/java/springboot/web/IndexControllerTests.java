package springboot.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT) // webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT 구성이 없을시 에러
public class IndexControllerTests {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void mainPageLoading() {
        String body = testRestTemplate.getForObject("/", String.class);

        assertThat(body).contains("스프링 부트로 만든 게시판");
    }
}
