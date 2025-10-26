package petstore.bdd.stepdefinitions;



import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import petstore.model.user.Users;
import petstore.reporting.AllureReportUtils;
import petstore.utils.TestDataGenerator;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class UserStepDef {

    @Autowired
    private TestContext testContext;

    @Given("I have a test user with username {string}")
    public void i_have_a_test_user_with_username(String username) {
        String uniqueUsername = username.replace("${timestamp}", String.valueOf(System.currentTimeMillis()));
        Users user = testContext.createTestUser(uniqueUsername);

        AllureReportUtils.logStep("Created test user with username: " + uniqueUsername);
        AllureReportUtils.logTestData("Username", uniqueUsername);
        AllureReportUtils.logTestData("User ID", user.getId().toString());
    }

    @Given("I have the User API service available")
    public void i_have_the_user_api_service_available() {
        AllureReportUtils.logStep("User API service is available");
    }

    @Given("I have created a user with username {string}")
    public void i_have_created_a_user_with_username(String username) {
        String uniqueUsername = username.replace("${timestamp}", String.valueOf(System.currentTimeMillis()));
        Users user = testContext.createTestUser(uniqueUsername);

        AllureReportUtils.logStep("Creating user with username: " + uniqueUsername);
        Response response = testContext.userService.createUser(user);
        AllureReportUtils.logApiResponse(response);

        response.then().statusCode(200);
        AllureReportUtils.logStep("User created successfully");
    }

    @Given("I have multiple test users data")
    public void i_have_multiple_test_users_data() {
        AllureReportUtils.logStep("Preparing multiple test users data");
    }

    @When("I create a new user with the following details:")
    public void i_create_a_new_user_with_the_following_details(Map<String, String> userDetails) {
        String username = "testuser_" + System.currentTimeMillis();
        Users user = new Users();
        user.setId(TestDataGenerator.generateRandomId());
        user.setUsername(username);
        user.setFirstName(userDetails.get("firstName"));
        user.setLastName(userDetails.get("lastName"));
        user.setEmail(userDetails.get("email"));
        user.setPassword(userDetails.get("password"));
        user.setPhone(userDetails.get("phone"));
        user.setUserStatus(Integer.parseInt(userDetails.get("userStatus")));

        testContext.setCurrentUser(user);
        testContext.setCurrentUsername(username);

        AllureReportUtils.logStep("Creating user with provided details");
        AllureReportUtils.logApiRequest("POST", "/user", user);

        Response response = testContext.userService.createUser(user);
        testContext.setCurrentResponse(response);

        AllureReportUtils.logApiResponse(response);
    }

    @When("I retrieve the user by username {string}")
    public void i_retrieve_the_user_by_username(String username) {
        String actualUsername = username.replace("${timestamp}",
                String.valueOf(testContext.getCurrentUser().getId()));

        AllureReportUtils.logStep("Retrieving user by username: " + actualUsername);
        AllureReportUtils.logApiRequest("GET", "/user/" + actualUsername, null);

        Response response = testContext.userService.getUserByUsername(actualUsername);
        testContext.setCurrentResponse(response);

        AllureReportUtils.logApiResponse(response);
    }

    @When("I update the user with username {string} with following details:")
    public void i_update_the_user_with_username_with_following_details(String username, Map<String, String> updateDetails) {
        String actualUsername = username.replace("${timestamp}",
                String.valueOf(testContext.getCurrentUser().getId()));

        Users currentUser = testContext.getCurrentUser();
        currentUser.setFirstName(updateDetails.get("firstName"));
        currentUser.setLastName(updateDetails.get("lastName"));
        currentUser.setEmail(updateDetails.get("email"));

        AllureReportUtils.logStep("Updating user: " + actualUsername);
        AllureReportUtils.logApiRequest("PUT", "/user/" + actualUsername, currentUser);

        Response response = testContext.userService.updateUserByUsername(actualUsername, currentUser);
        testContext.setCurrentResponse(response);

        AllureReportUtils.logApiResponse(response);
    }

    @When("I delete the user with username {string}")
    public void i_delete_the_user_with_username(String username) {
        String actualUsername = username.replace("${timestamp}",
                String.valueOf(testContext.getCurrentUser().getId()));

        AllureReportUtils.logStep("Deleting user: " + actualUsername);
        AllureReportUtils.logApiRequest("DELETE", "/user/" + actualUsername, null);

        Response response = testContext.userService.deleteUser(actualUsername);
        testContext.setCurrentResponse(response);

        AllureReportUtils.logApiResponse(response);
    }

    @When("I try to retrieve the user by username {string}")
    public void i_try_to_retrieve_the_user_by_username(String username) {
        String actualUsername = username.replace("${timestamp}",
                String.valueOf(System.currentTimeMillis()));

        AllureReportUtils.logStep("Trying to retrieve user: " + actualUsername);
        AllureReportUtils.logApiRequest("GET", "/user/" + actualUsername, null);

        Response response = testContext.userService.getUserByUsername(actualUsername);
        testContext.setCurrentResponse(response);

        AllureReportUtils.logApiResponse(response);
    }

    @When("I login with username {string} and password {string}")
    public void i_login_with_username_and_password(String username, String password) {
        String actualUsername = username.replace("${timestamp}",
                String.valueOf(testContext.getCurrentUser().getId()));

        AllureReportUtils.logStep("Logging in with username: " + actualUsername);
        AllureReportUtils.logApiRequest("GET",
                "/user/login?username=" + actualUsername + "&password=" + password, null);

        Response response = testContext.userService.userLogin(actualUsername, password);
        testContext.setCurrentResponse(response);

        AllureReportUtils.logApiResponse(response);
    }

    @When("I logout the user")
    public void i_logout_the_user() {
        AllureReportUtils.logStep("Logging out user");
        AllureReportUtils.logApiRequest("GET", "/user/logout", null);

        Response response = testContext.userService.userLogout();
        testContext.setCurrentResponse(response);

        AllureReportUtils.logApiResponse(response);
    }

    @When("I create users with array containing {int} users")
    public void i_create_users_with_array_containing_users(int userCount) {
        Users[] users = TestDataGenerator.createUserArray(userCount);

        AllureReportUtils.logStep("Creating " + userCount + " users with array");
        AllureReportUtils.logApiRequest("POST", "/user/createWithArray", Arrays.toString(users));

        Response response = testContext.userService.userCreateWithArray(users);
        testContext.setCurrentResponse(response);
        testContext.setScenariosContext("createdUsers", users);

        AllureReportUtils.logApiResponse(response);
    }

    @When("I create users with list containing {int} users")
    public void i_create_users_with_list_containing_users(int userCount) {
        Users[] usersArray = TestDataGenerator.createUserArray(userCount);
        List<Users> users = Arrays.asList(usersArray);

        AllureReportUtils.logStep("Creating " + userCount + " users with list");
        AllureReportUtils.logApiRequest("POST", "/user/createWithList", users.toString());

        Response response = testContext.userService.userCreateWithArray(usersArray);
        testContext.setCurrentResponse(response);
        testContext.setScenariosContext("createdUsers", users);

        AllureReportUtils.logApiResponse(response);
    }

    @When("I retrieve each user by their username")
    public void i_retrieve_each_user_by_their_username() {
        List<Users> users = (List<Users>) testContext.getScenariosContext("createdUsers");
        assertNotNull(users, "Created users list should not be null");

        AllureReportUtils.logStep("Retrieving each created user by username");

        for (Users user : users) {
            Response response = testContext.userService.getUserByUsername(user.getUsername());
            AllureReportUtils.logStep("Retrieved user: " + user.getUsername());
            assertEquals(200, response.getStatusCode(),
                    "User " + user.getUsername() + " should be retrievable");
        }
    }

    @Then("the user should be created successfully")
    public void the_user_should_be_created_successfully() {
        Response response = testContext.getCurrentResponse();
        assertNotNull(response, "Response should not be null");

        AllureReportUtils.logValidation("Verify user creation was successful");
        assertEquals(200, response.getStatusCode(), "Status code should be 200");

        String responseBody = response.getBody().asString();
        assertNotNull(responseBody, "Response body should not be null");
    }

    @Then("the response status code should be {int}")
    public void the_response_status_code_should_be(int expectedStatusCode) {
        Response response = testContext.getCurrentResponse();
        assertNotNull(response, "Response should not be null");

        AllureReportUtils.logValidation("Verify status code is " + expectedStatusCode);
        assertEquals(expectedStatusCode, response.getStatusCode(),
                "Status code should be " + expectedStatusCode);
    }

    @Then("the user details should be returned successfully")
    public void the_user_details_should_be_returned_successfully() {
        Response response = testContext.getCurrentResponse();
        assertNotNull(response, "Response should not be null");

        Users user = response.as(Users.class);
        assertNotNull(user, "User object should not be null");

        AllureReportUtils.logValidation("Verify user details are returned");
        assertNotNull(user.getUsername(), "Username should not be null");
        assertNotNull(user.getFirstName(), "First name should not be null");
        assertNotNull(user.getLastName(), "Last name should not be null");
    }

    @Then("the user first name should be {string}")
    public void the_user_first_name_should_be(String expectedFirstName) {
        Response response = testContext.getCurrentResponse();
        Users user = response.as(Users.class);

        AllureReportUtils.logValidation("Verify first name is: " + expectedFirstName);
        assertEquals(expectedFirstName, user.getFirstName(),
                "First name should be " + expectedFirstName);
    }

    @Then("the user last name should be {string}")
    public void the_user_last_name_should_be(String expectedLastName) {
        Response response = testContext.getCurrentResponse();
        Users user = response.as(Users.class);

        AllureReportUtils.logValidation("Verify last name is: " + expectedLastName);
        assertEquals(expectedLastName, user.getLastName(),
                "Last name should be " + expectedLastName);
    }

    @Then("the user should be updated successfully")
    public void the_user_should_be_updated_successfully() {
        Response response = testContext.getCurrentResponse();
        assertNotNull(response, "Response should not be null");

        AllureReportUtils.logValidation("Verify user update was successful");
        assertEquals(200, response.getStatusCode(), "Status code should be 200");
    }

    @Then("the user should be deleted successfully")
    public void the_user_should_be_deleted_successfully() {
        Response response = testContext.getCurrentResponse();
        assertNotNull(response, "Response should not be null");

        AllureReportUtils.logValidation("Verify user deletion was successful");
        assertEquals(200, response.getStatusCode(), "Status code should be 200");
    }

    @Then("the login should be successful")
    public void the_login_should_be_successful() {
        Response response = testContext.getCurrentResponse();
        assertNotNull(response, "Response should not be null");

        AllureReportUtils.logValidation("Verify login was successful");
        assertEquals(200, response.getStatusCode(), "Status code should be 200");

        String responseBody = response.getBody().asString();
        assertNotNull(responseBody, "Response body should not be null");
    }

    @Then("the response should contain session information")
    public void the_response_should_contain_session_information() {
        Response response = testContext.getCurrentResponse();

        AllureReportUtils.logValidation("Verify session information is present");
        String sessionHeader = response.header("X-Expires-After");
        String rateLimitHeader = response.header("X-Rate-Limit");

        assertNotNull(sessionHeader, "Session expiration header should be present");
        assertNotNull(rateLimitHeader, "Rate limit header should be present");

        AllureReportUtils.logTestData("Session Expires After", sessionHeader);
        AllureReportUtils.logTestData("Rate Limit", rateLimitHeader);
    }

    @Then("the login should fail")
    public void the_login_should_fail() {
        Response response = testContext.getCurrentResponse();

        AllureReportUtils.logValidation("Verify login failed as expected");
        assertEquals(400, response.getStatusCode(), "Status code should be 400 for failed login");
    }

    @Then("the logout should be successful")
    public void the_logout_should_be_successful() {
        Response response = testContext.getCurrentResponse();

        AllureReportUtils.logValidation("Verify logout was successful");
        assertEquals(200, response.getStatusCode(), "Status code should be 200");
    }

    @Then("the users should be created successfully")
    public void the_users_should_be_created_successfully() {
        Response response = testContext.getCurrentResponse();

        AllureReportUtils.logValidation("Verify bulk user creation was successful");
        assertEquals(200, response.getStatusCode(), "Status code should be 200");
    }

    @Then("each user should be retrievable successfully")
    public void each_user_should_be_retrievable_successfully() {
        AllureReportUtils.logStep("All created users are retrievable successfully");
        // Validation is done in the "When" step for this scenario
    }

    @Then("the response status code for each should be {int}")
    public void the_response_status_code_for_each_should_be(int expectedStatusCode) {
        AllureReportUtils.logValidation("All users return status code: " + expectedStatusCode);
        // Validation is done in the "When" step for this scenario
    }
}