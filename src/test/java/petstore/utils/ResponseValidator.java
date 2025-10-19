package petstore.utils;

import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import petstore.model.Pet;
import petstore.model.store.Order;
import petstore.model.user.UserResponse;
import petstore.model.user.Users;

public class ResponseValidator {

    public static  void validatePetResponse(Response response , Pet expectedPet){

        response.then()
                .statusCode(200);

        Pet actualPet = response.as(Pet.class);

        Assertions.assertEquals(expectedPet.getId() , actualPet.getId(),"Pet ID should match");
        Assertions.assertEquals(expectedPet.getName() ,actualPet.getName(), "Pet name should match");
        Assertions.assertEquals(expectedPet.getStatus(),actualPet.getStatus(),"Pet status should match");
    }

    public  static  void validateNotFoundResponse(Response response){

        response.then().statusCode(404);
    }

    //Store validation Method
    public static  void validateOrderResponse(Response response , Order expectedOrder){

        response.then().statusCode(200);

        Order actualOrder = response.as(Order.class);

        Assertions.assertEquals(expectedOrder.getId(), actualOrder.getId() ,"Order Id should match");
        Assertions.assertEquals(expectedOrder.getPetId(),actualOrder.getPetId(),"Pet Id should match");
    }

    public  static  void validateOrderNotFound(Response response){
        response.then().statusCode(404);
    }

    //User Validation Method
    public  static  void validateUserResponse(Response response , Users expectedUser){

        response.then().statusCode(200);

        Users actualUser = response.as(Users.class);

        Assertions.assertEquals(expectedUser.getUsername() , actualUser.getUsername(),"verified username should match");
        Assertions.assertEquals(expectedUser.getPassword() , actualUser.getPassword(),"Verified password should match");
        Assertions.assertEquals(expectedUser.getId(), actualUser.getId(),"Verified User ID should match");
        Assertions.assertEquals(expectedUser.getFirstName(), actualUser.getFirstName() , "Verified firstName should match");
    }

    public  static  void validateUserCreated(Response response){
        response.then().statusCode(200);

        UserResponse userResponse = response.as(UserResponse.class);
        Assertions.assertNotNull(userResponse, "User response should not be null");
        Assertions.assertNotNull(userResponse.getMessage(), "Message should not be null");
        Assertions.assertNotNull(userResponse.getMessage(), "Message should not be null");
    }

    public  static  void validateUserNotFound(Response response){
        response.then().statusCode(404);
    }

    public static  void validateUserDeleted(Response response){
        response.then().statusCode(200);
    }

    public  static  void validateLogoutSuccess(Response response){
        response.then().statusCode(200);
    }


}
