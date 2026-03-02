package week1.taco;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import week1.taco.repository.UserRepository;

@SpringBootTest
class ApplicationTests {

    @Autowired
    UserRepository userRepository;

    @Test
    void contextLoads() {
    }

    @Test
    void defaultUserLoaded() {
        assertThat(userRepository.findByUsername("user")).isPresent();
    }

}
