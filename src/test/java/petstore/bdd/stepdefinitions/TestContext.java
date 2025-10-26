package petstore.bdd.stepdefinitions;

import io.restassured.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import petstore.PetStoreService.UserService;
import petstore.model.user.Users;
import petstore.utils.TestDataGenerator;

import java.util.HashMap;
import java.util.Map;

@Component
public class TestContext {

    @Autowired
    public UserService userService;

    private Map<String , Object> scenariosContext = new HashMap<>();
    private Users currentUser;
    private Response currentResponse ;
    private String currentUsername;

    public Users getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(Users currentUser) {
        this.currentUser = currentUser;
    }

    public Map<String, Object> getScenariosContext(String key) {
        return scenariosContext;
    }

    public void setScenariosContext(String key , Object value) {
        this.scenariosContext = scenariosContext;
    }

    public Response getCurrentResponse() {
        return currentResponse;
    }

    public void setCurrentResponse(Response currentResponse) {
        this.currentResponse = currentResponse;
    }

    public String getCurrentUsername() {
        return currentUsername;
    }

    public void setCurrentUsername(String currentUsername) {
        this.currentUsername = currentUsername;
    }

    public  Users createTestUser(String username){

        Users users = TestDataGenerator.createDefaultUser(username);
        setCurrentUser(users);
        setCurrentUsername(username);

        return  users;
    }

    public void cleanUpTestUser(String username){

        try {
            if(username != null){
                userService.deleteUser(username);
            }
        }catch (Exception e){

            System.out.println("Error Message :"+e.getMessage());
        }
    }

    public void reset(){
        scenariosContext.clear();
        currentUser = null;
        currentResponse = null;
        currentUsername = null;
    }
}
