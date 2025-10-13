package petstore.client;

import io.restassured.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import petstore.PetStoreService.PetStoreService;
import petstore.model.Pet;

@Component
public class PetStoreClient {

    @Autowired
    private PetStoreService petStoreService;

    public Pet cretePetAndGetResponse(Long petId , String name , String status){

        Pet pet = new Pet(petId, name ,status);
        Response response = petStoreService.addPet(pet);

        return  response.then()
                .statusCode(200)
                .extract().as(Pet.class);
    }

    public boolean isPetAvailable(Long petId){
        Response response = petStoreService.getPetById(petId);
        return response.getStatusCode() == 200;
    }

}
