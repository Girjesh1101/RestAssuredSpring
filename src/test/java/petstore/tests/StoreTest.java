package petstore.tests;

import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import petstore.PetStoreService.StoreService;
import petstore.base.BaseTest;
import petstore.model.store.Order;
import petstore.utils.ResponseValidator;
import petstore.utils.TestDataGenerator;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class StoreTest extends BaseTest {

    @Autowired
    private StoreService storeService;

    private  static  Long orderId;
    private static  Long petId;
    private Order testOrder;

    @BeforeAll
    public  void setUpTestData(){
        orderId = TestDataGenerator.generateRandomId();
        petId = TestDataGenerator.generateRandomId();
        testOrder  = TestDataGenerator.createDefaultOrder(orderId, petId);

        System.out.println("Order ID : "+ orderId);
        System.out.println("Pet ID : "+petId);
    }

    @Test
    @org.junit.jupiter.api.Order(1)
    @DisplayName("should place an order for pet")
    public  void testPlaceOrder(){

        System.out.println("Store Service instace : "+storeService);

        Response response = storeService.placeOrder(testOrder);
        ResponseValidator.validateOrderResponse(response,testOrder);
    }

    @Test
    @org.junit.jupiter.api.Order(2)
    @DisplayName("should retrive order by Id")
    void testGetOrderById(){
        Response response = storeService.getOrderById(orderId);
        ResponseValidator.validateOrderResponse(response, testOrder);
    }

    @Test
    @org.junit.jupiter.api.Order(3)
    @DisplayName("should get store inventory")
    void testGetInventory(){

        Response response = storeService.getInventory();
        response.then().log().all();
        Assertions.assertEquals(response.statusCode() , 200 ,"inventory should contain data");
    }

    @Test
    @org.junit.jupiter.api.Order(4)
    @DisplayName("shold delete an order")
    void deleteOrder(){

        Response response = storeService.deleteOrder(orderId);
        response.then().log().all();
        Assertions.assertEquals(response.statusCode(), 200 , "order should deleted");

        //Verify order is deleted
        Response getResponse  = storeService.getOrderById(orderId);
        ResponseValidator.validateOrderNotFound(getResponse);
    }

    @Test
    @DisplayName("should return 404 for non-existing order")
    void nonExistingOrder(){

        Long nonExistingOrderId  = 9999999L;
        Response response = storeService.getOrderById(nonExistingOrderId);
        ResponseValidator.validateOrderNotFound(response);
    }

    @Test
    @DisplayName("should handle order with different status")
    void testOrderWithDiffStatus(){

        String[] statuses ={"placed" , "approved" , "delivered"};

        for(String status : statuses){
            Long orderId = TestDataGenerator.generateRandomId();
            Order order = TestDataGenerator.createOrder(orderId , petId , 1,status, true);

            Response placeResponse = storeService.placeOrder(order);
            ResponseValidator.validateOrderResponse(placeResponse, order);

            storeService.deleteOrder(orderId);
        }
    }


}
