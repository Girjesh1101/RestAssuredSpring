package petstore.utils;

import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import petstore.model.Pet;
import petstore.model.store.Order;

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

}
