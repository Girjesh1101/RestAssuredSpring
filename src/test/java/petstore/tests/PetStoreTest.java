package petstore.tests;


import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import petstore.PetStoreService.PetStoreService;
import petstore.base.BaseTest;
import petstore.model.Pet;
import petstore.utils.ResponseValidator;
import petstore.utils.TestDataGenerator;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PetStoreTest extends BaseTest {

    @Autowired
    private PetStoreService petStoreService;

    private  static Long petId;
    private Pet testPet;

    @BeforeAll
    void setUpTestData(){

        petId  = TestDataGenerator.generateRandomId();
        testPet = TestDataGenerator.createPet(petId , "TestPet", "available");
    }

    @Test
    @Order(1)
    @DisplayName("Should add a new pet to store")
    void testAddPet(){

        Response response = petStoreService.addPet(testPet);
        ResponseValidator.validatePetResponse(response ,testPet);
    }

    @Test
    @Order(2)
    @DisplayName("Should retrieve pet by ID")
    void testGetPetByID(){

        System.out.println("PetId : "+petId);
        Response response = petStoreService.getPetById(petId);
        ResponseValidator.validatePetResponse(response , testPet);
    }

    @Test
    @Order(3)
    @DisplayName("Should update pet information")
    void testUpdatePet(){

        testPet.setName("UpdatePet");
        testPet.setStatus("sold");

        Response response = petStoreService.updatePet(testPet);
        ResponseValidator.validatePetResponse(response,testPet);
    }

    @Test
    @Order(4)
    @DisplayName("Should find pets by status")
    void testFindPetsByStatus(){

        Response response = petStoreService.findPetsByStatus("sold");
        response.then().statusCode(200);

        Pet[] pets =response.as(Pet[].class);
        Assertions.assertTrue(pets.length>0 , "Should return at least one pet");
    }

    @Test
    @Order(5)
    @DisplayName("Should delete a pet")
    void testDeletePet(){

        Response response = petStoreService.deletePet(petId);
        response.then().statusCode(200);

        Response getResponse = petStoreService.getPetById(petId);
        ResponseValidator.validateNotFoundResponse(getResponse);
    }

    @Test
    @DisplayName("Should return 404 for non-existent pet")
    void getNonExistentPet(){

        Long nonExistentId = 99999999L;
        Response response = petStoreService.getPetById(nonExistentId);
        ResponseValidator.validateNotFoundResponse(response);
    }


}
