package springboot.config.auth;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SecurityConfigTests {

    @Autowired
    private Environment environment;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void profile_api_is_called_without_certification() {
        List<String> profiles = Arrays.asList(environment.getActiveProfiles());

        ResponseEntity<String> responseEntity = testRestTemplate.getForEntity("/profile", String.class);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isIn(profiles);
    }
}
