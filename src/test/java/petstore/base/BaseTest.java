package petstore.base;

import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import petstore.config.TestConfig;
import petstore.reporting.AllureReportUtils;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestConfig.class)
public class BaseTest {

    @BeforeEach
    protected void setUp(TestInfo testInfo){
        System.out.println("Starting test : "+ testInfo.getDisplayName());
        String testName = testInfo.getDisplayName();
        String testTags = String.join(", ", testInfo.getTags());

        AllureReportUtils.logStep("Starting test: " + testName);
        if (!testTags.isEmpty()) {
            AllureReportUtils.logStep("Test Tags: " + testTags);
        }
    }

    protected void logApiCall(String method, String url, Object requestBody) {
        AllureReportUtils.logApiRequest(method, url, requestBody);
    }

    protected void logApiResponse(Response response) {
        AllureReportUtils.logApiResponse(response);
    }

    protected void logValidation(String message) {
        AllureReportUtils.logValidation(message);
    }

    protected void logTestStep(String step) {
        AllureReportUtils.logStep(step);
    }

    protected void logTestData(String dataName, String dataValue) {
        AllureReportUtils.logTestData(dataName, dataValue);
    }

    protected void logSuccess(String message) {
        AllureReportUtils.logSuccess(message);
    }

    protected void logError(String message) {
        AllureReportUtils.logError(message);
    }
}
