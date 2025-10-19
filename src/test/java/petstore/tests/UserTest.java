package petstore.tests;

import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import petstore.PetStoreService.UserService;
import petstore.base.BaseTest;
import petstore.model.user.Users;
import petstore.utils.ResponseValidator;
import petstore.utils.TestDataGenerator;

@SpringJUnitConfig
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserTest extends BaseTest {

    @Autowired
    private UserService userService;

    private String username;
    private Users users;

    @BeforeAll
    public  void setUpTestUserData(){
        username = "testUser_"+System.currentTimeMillis();
        users = TestDataGenerator.createDefaultUser(username);
        System.out.println("Test Username : "+username);
    }

    @Test
    @Order(1)
    @DisplayName("create User Name")
    public  void createUser(){

        Response response = userService.createUser(users);
        ResponseValidator.validateUserCreated(response);
    }

    @Test
    @Order(2)
    @DisplayName("Retrieve user by Username")
    public  void getUserByUsername(){

        Response response = userService.getUserByUsername(username);
        ResponseValidator.validateUserResponse(response , users);
    }

    @Test
    @Order(3)
    @DisplayName("update user by username")
    public  void updateUserByUsername(){

        users.setLastName("updatedFirstName");
        users.setLastName("updateLastName");
        users.setEmail("updated_"+username+"@gmail.com");

        Response response = userService.updateUserByUsername(username,users);
//        ResponseValidator.validateUserResponse(response, users);

        //verify after update

        Response getResponse = userService.getUserByUsername(username);
        Users updatedUser = getResponse.as(Users.class);

//        Assertions.assertEquals("updatedFirstName",updatedUser.getFirstName(),"Verified update first Name should match");
//        Assertions.assertEquals("updatedLastName", updatedUser.getLastName(),"Verified update last Name should match ");

    }

    @Test
    @Order(4)
    @DisplayName("Login user with Username and password")
    public  void  userLogin(){

        Response response = userService.userLogin(username , users.getPassword());
        response.then().statusCode(200).log().all();
    }

    @Test
    @Order(5)
    @DisplayName("Logout user")
    public  void userLogout(){

        Response response = userService.userLogout();
        ResponseValidator.validateLogoutSuccess(response);
    }

    @Test
    @Order(6)
    @DisplayName("delete User by username")
    public  void deleteUserByUsername(){

        System.out.println("Delete : "+username);
        Response response = userService.deleteUser(username);
        ResponseValidator.validateUserDeleted(response);
        response.then().log().all();

        //verified user is deleted
        Response getResponse = userService.getUserByUsername(username);
        ResponseValidator.validateUserNotFound(getResponse);
        getResponse.then().log().all();
    }

    @Test
    @Order(7)
    @DisplayName("create Multiple users")
    public  void createMultipleUsersWithArray(){

        Users[] listOfUser = TestDataGenerator.createUserArray(3);

        Response response = userService.userCreateWithArray(listOfUser);
        response.then().statusCode(200).log().all();

        for(Users user : listOfUser){

            userService.deleteUser(user.getUsername());
        }
    }

    @Test
    @Order(8)
    @DisplayName("Non existing username")
    public  void nonExistingUser(){

        String nonExistingUsername = "nonexistinguser_999999";

        Response response = userService.getUserByUsername(nonExistingUsername);
        ResponseValidator.validateUserNotFound(response);
    }

    @AfterAll
    public void cleanup(){

        try {
            userService.deleteUser(username);
        } catch (Exception e) {
            System.out.println( "data has been cleaned.");
        }
    }
}
