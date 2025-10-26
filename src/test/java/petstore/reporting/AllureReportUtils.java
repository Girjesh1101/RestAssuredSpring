package petstore.reporting;

import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;

public class AllureReportUtils {

    @Step("{stepName}")
    public static void logStep(String stepName) {
        // This annotation automatically creates steps in Allure report
    }

    public static void logApiRequest(String method, String url, Object requestBody) {
        Allure.step("API Request: " + method + " " + url, () -> {
            if (requestBody != null) {
                String jsonBody = requestBody.toString();
                Allure.addAttachment("Request Body", "application/json",
                        new ByteArrayInputStream(jsonBody.getBytes(StandardCharsets.UTF_8)), ".json");
            }
        });
    }

    public static void logApiResponse(Response response) {
        Allure.step("API Response: " + response.getStatusCode(), () -> {
            // Response Status and Time
            String statusInfo = "Status Code: " + response.getStatusCode() +
                    "\nResponse Time: " + response.getTime() + "ms" +
                    "\nStatus Line: " + response.getStatusLine();

            Allure.addAttachment("Response Info", "text/plain",
                    new ByteArrayInputStream(statusInfo.getBytes(StandardCharsets.UTF_8)).toString());

            // Response Body
            String responseBody = response.getBody().asPrettyString();
            Allure.addAttachment("Response Body", "application/json",
                    new ByteArrayInputStream(responseBody.getBytes(StandardCharsets.UTF_8)), ".json");

            // Response Headers
            String headers = response.getHeaders().toString();
            Allure.addAttachment("Response Headers", "text/plain",
                    new ByteArrayInputStream(headers.getBytes(StandardCharsets.UTF_8)).toString());
        });
    }

    public static void logValidation(String validationMessage) {
        Allure.step("Validation: " + validationMessage);
    }

    public static void logTestData(String dataName, String dataValue) {
        Allure.addAttachment(dataName, "text/plain",
                new ByteArrayInputStream(dataValue.getBytes(StandardCharsets.UTF_8)).toString());
    }

    public static void attachJson(String name, String jsonContent) {
        Allure.addAttachment(name, "application/json",
                new ByteArrayInputStream(jsonContent.getBytes(StandardCharsets.UTF_8)), ".json");
    }

    public static void logError(String errorMessage) {
        Allure.step("Error: " + errorMessage);
    }

    public static void logSuccess(String successMessage) {
        Allure.step("Success: " + successMessage);
    }
}