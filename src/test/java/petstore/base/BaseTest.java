package petstore.base;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import petstore.config.TestConfig;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestConfig.class)
public class BaseTest {

    @BeforeEach
    protected void setUp(TestInfo testInfo){
        System.out.println("Starting test : "+ testInfo.getDisplayName());

    }
}
