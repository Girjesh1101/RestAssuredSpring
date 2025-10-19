package petstore.PetStoreService;

import io.restassured.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import petstore.model.user.Users;

import static io.restassured.RestAssured.*;

@Service
public class UserService {

    @Autowired
    private io.restassured.specification.RequestSpecification requestSpecification;

    private  static  String createUser ="/user";

    private  static  String getUserByUsername ="/user/{username}";

    private  static  String updateUserByUsername = "/user/{username}";

    private  static  String deleteUser = "/user/{username}";

    private  static  String user_login = "/user/login";

    private  static  String user_logout ="/user/logout";

    private  static  String createUserWithArray = "/user/createWithArray";


    public Response createUser(Users users){

        return  given()
                .spec(requestSpecification)
                .body(users)
                .when()
                .post(createUser);
    }

    public Response getUserByUsername(String userName){
        return given()
                .spec(requestSpecification)
                .pathParam("username",userName)
                .when().get(getUserByUsername);
    }

    public  Response updateUserByUsername(String username , Users users){

        return  given()
                .spec(requestSpecification)
                .pathParam("username", username)
                .body(users)
                .when()
                .put(updateUserByUsername);
    }

    public Response deleteUser(String username){
        return  given()
                .spec(requestSpecification)
                .pathParam("username",username)
                .when()
                .delete(deleteUser);
    }

    public  Response userLogin(String username , String password){

        return given()
                .spec(requestSpecification)
                .queryParams("username", username)
                .queryParams("password", password)
                .when()
                .get(user_login);
    }

    public  Response userLogout(){

        return  given()
                .spec(requestSpecification)
                .when()
                .get(user_logout);
    }

    public  Response userCreateWithArray(Users[] users){

        return  given()
                .spec(requestSpecification)
                .body(users)
                .when()
                .post(createUserWithArray);
    }
}
