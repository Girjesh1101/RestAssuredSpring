package petstore;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.catalina.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import petstore.model.user.Users;
import petstore.utils.TestDataGenerator;

import static io.restassured.RestAssured.given;

public class SimpleFIle {


    @Test
    void testCreateUserSimple() {
        RestAssured.baseURI = "https://petstore.swagger.io/v2";

        String username = "testuser_" + System.currentTimeMillis();
        Users users = TestDataGenerator.createDefaultUser(username);

        Response response = given()
                .contentType(ContentType.JSON)
                .body(users)
                .when()
                .post("/user");

        Assertions.assertEquals(200, response.getStatusCode());
        System.out.println("User created successfully: " + username);
    }
}



