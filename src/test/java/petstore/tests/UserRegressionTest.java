package petstore.tests;

import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import petstore.PetStoreService.UserService;
import petstore.base.BaseTest;
import petstore.config.TestConfig;
import petstore.model.user.Users;
import petstore.utils.TestDataGenerator;

@SpringJUnitConfig(TestConfig.class)
@TestPropertySource("classpath:application-test.properties")
@Tag("regression")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserRegressionTest extends BaseTest {

    @Autowired
    private UserService userService;

    @Test
    @DisplayName("User:regression complete cycle")
    void completeUserCycle(){

        String username = "Regression"+System.currentTimeMillis();
        Users users = TestDataGenerator.createDefaultUser(username);

        //Step 1 : create user
        Response response = userService.createUser(users);
        response.then().statusCode(200).log().all();

        //Step 2 : retrieve user by username

        Response getResponse = userService.getUserByUsername(username);
        getResponse.then().statusCode(200);

        Users createdUser = getResponse.as(Users.class);
        Assertions.assertEquals(users.getFirstName() , createdUser.getFirstName() ,"Verify first name should match" );
        Assertions.assertEquals(users.getLastName() , createdUser.getLastName() , "Verify last name should match");
        Assertions.assertEquals(users.getUsername(), createdUser.getUsername(), "Verify username should match");

        //Step 3 : Login User

        Response loginResponse = userService.userLogin(username,users.getPassword());
        loginResponse.then().statusCode(200);

        // Step 4 : Logout User

        Response logoutResponse = userService.userLogout();
        loginResponse.then().statusCode(200).log().all();

//       Step 5 : Update user

        users.setEmail("updated_"+username+"@gmail.com");
        users.setPhone("+91-920-982-8873");
        Response updateResponse = userService.updateUserByUsername(username, users);
        updateResponse.then().statusCode(200).log().all();

        // Step 6 : verified updated

        Response updatedGetResponse = userService.getUserByUsername(username);
        updatedGetResponse.then().statusCode(200).log().all();
        Users updatedUser = updateResponse.as(Users.class);
        Assertions.assertEquals("updated_"+username+"@gmail.com" , updatedUser.getEmail());
        Assertions.assertEquals("+91-920-982-8873", updatedUser.getPhone());

        //Step 7 : delete user

        Response deleteResponse = userService.deleteUser(username);
        deleteResponse.then().statusCode(200).log().all();

        // Step 8 : verify user Successfully deleted

        Response finalResponse  = userService.getUserByUsername(username);
        finalResponse.then().statusCode(404).log().all();

    }

    @Test
    @DisplayName("Multiple user creation : regression")
    public  void multipleCreateUser(){

        for (int i = 0; i < 3; i++) {

            Users user = TestDataGenerator.createRandomUser();
            Response response = userService.createUser(user);
            response.then().statusCode(200).log().all();

            //retrieve multiple user created

            Response getResponse = userService.getUserByUsername(user.getUsername());
            getResponse.then().statusCode(200);

            //clean up

            Response deleteUser = userService.deleteUser(user.getUsername());
            deleteUser.then().statusCode(200);

        }
    }

    @Test
    @DisplayName("login with invalid credential")
    public  void invalidLogin(){

        String username = "invalid"+System.currentTimeMillis();

        Users user = TestDataGenerator.createDefaultUser(username);

        Response response = userService.userLogin(username, user.getPassword());
        response.then().statusCode(400);
    }
}
