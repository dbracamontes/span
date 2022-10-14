package cucumber;

import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import com.span.challenge.SpanChallengeApplication;
import io.cucumber.spring.CucumberContextConfiguration;

@CucumberContextConfiguration
@SpringBootTest(classes = SpanChallengeApplication.class)
@ContextConfiguration(classes = SpanChallengeApplication.class, loader = SpringBootContextLoader.class)
public class CucumberSpringConfiguration {
}