package petstore.PetStoreService;

import io.restassured.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import petstore.model.Pet;

import static io.restassured.RestAssured.*;

@Service
public class PetStoreService {


    @Autowired
    private io.restassured.specification.RequestSpecification requestSpecification;

    private  static  final String PET_PATH= "/pet";
    private  static  final String PET_BY_ID_PATH = "/pet/{petId}";
    private  static  final  String PET_BY_STATUS_PATH = "/pet/findByStatus";

    public  Response addPet(Pet pet){
        return given()
                .spec(requestSpecification)
                .body(pet)
                .when()
                .post(PET_PATH);
    }

    public Response getPetById(Long petId){
        return  given()
                .spec(requestSpecification)
                .pathParam("petId",petId)
                .when()
                .get(PET_BY_ID_PATH);
    }

    public  Response updatePet(Pet pet){
        return  given()
                .spec(requestSpecification)
                .body(pet)
                .when()
                .put(PET_PATH);
    }

    public  Response deletePet(Long petId){
        return  given()
                .spec(requestSpecification)
                .pathParam("petId",petId)
                .when()
                .delete(PET_BY_ID_PATH);
    }

    public  Response findPetsByStatus(String status){
        return given()
                .spec(requestSpecification)
                .queryParams("status",status)
                .when()
                .get(PET_BY_STATUS_PATH);
    }
}
