package petstore.utils;

import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import petstore.model.Pet;

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
}
