//package petstore.tests;
//
//
//import io.restassured.response.Response;
//import org.junit.jupiter.api.*;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
//import petstore.PetStoreService.StoreService;
//import petstore.base.BaseTest;
//import petstore.config.TestConfig;
//import petstore.utils.TestDataGenerator;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringJUnitConfig(TestConfig.class)
//@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
//public class StoreAPITest extends BaseTest {
//
//    @Autowired
//    private StoreService storeService;
//
//    private Long testOrderId;
//    private Long testPetId;
//    private Order testOrder;
//
//    @BeforeAll
//    void setupTestData() {
//        testOrderId = TestDataGenerator.generateRandomId();
//        testPetId = TestDataGenerator.generateRandomId();
//        testOrder  = (Order) TestDataGenerator.createDefaultOrder(testOrderId ,testPetId);
//
//        logTestStep("Test data setup completed");
//        logTestData("Test Order ID", testOrderId.toString());
//        logTestData("Test Pet ID", testPetId.toString());
//        logTestData("Test Order", testOrder.toString());
//    }
//
//    @Test
//    @org.junit.jupiter.api.Order(1)
//    @DisplayName("Should place an order for a pet")
//    void testPlaceOrder() {
//        logTestStep("Placing order for pet");
//
//        logApiCall("POST", "/store/order", testOrder);
//        Response response = storeService.placeOrder((petstore.model.store.Order) testOrder);
//        logApiResponse(response);
//
//        response.then().statusCode(200);
//
//        Order createdOrder = response.as(Order.class);
//
//        logValidation("Verify order ID matches");
//        assertEquals(testOrder.getID(), createdOrder.getId());
//
//        logValidation("Verify pet ID matches");
//        assertEquals(testOrder.getPetId(), createdOrder.getPetId());
//
//        logValidation("Verify order status is 'placed'");
//        assertEquals("placed", createdOrder.getStatus());
//
//        logValidation("Verify complete flag is true");
//        assertTrue(createdOrder.getComplete());
//
//        logSuccess("Order placed successfully");
//    }
//
//    @Test
//    @org.junit.jupiter.api.Order(2)
//    @DisplayName("Should retrieve order by ID")
//    void testGetOrderById() {
//        logTestStep("Retrieving order by ID");
//
//        logApiCall("GET", "/store/order/" + testOrderId, null);
//        Response response = storeService.getOrderById(testOrderId);
//        logApiResponse(response);
//
//        response.then().statusCode(200);
//
//        Order retrievedOrder = response.as(Order.class);
//
//        logValidation("Verify retrieved order ID matches");
//        assertEquals(testOrder.getId(), retrievedOrder.getId());
//
//        logValidation("Verify retrieved pet ID matches");
//        assertEquals(testOrder.getPetId(), retrievedOrder.getPetId());
//
//        logValidation("Verify order quantity matches");
//        assertEquals(testOrder.getQuantity(), retrievedOrder.getQuantity());
//
//        logSuccess("Order retrieved successfully");
//    }
//
//    @Test
//    @org.junit.jupiter.api.Order(3)
//    @DisplayName("Should get store inventory")
//    void testGetInventory() {
//        logTestStep("Retrieving store inventory");
//
//        logApiCall("GET", "/store/inventory", null);
//        Response response = storeService.getInventory();
//        logApiResponse(response);
//
//        response.then().statusCode(200);
//
//        com.petstore.model.store.Inventory inventory = response.as(com.petstore.model.store.Inventory.class);
//
//        logValidation("Verify inventory is not null");
//        assertNotNull(inventory, "Inventory should not be null");
//
//        logValidation("Verify inventory contains data");
//        assertTrue(inventory.getInventoryMap().size() > 0, "Inventory should contain data");
//
//        logTestData("Inventory Size", String.valueOf(inventory.getInventoryMap().size()));
//        logTestData("Available Pets", String.valueOf(inventory.getQuantity("available")));
//        logTestData("Pending Pets", String.valueOf(inventory.getQuantity("pending")));
//        logTestData("Sold Pets", String.valueOf(inventory.getQuantity("sold")));
//
//        logSuccess("Inventory retrieved successfully");
//    }
//
//    @Test
//    @org.junit.jupiter.api.Order(4)
//    @DisplayName("Should delete an order")
//    void testDeleteOrder() {
//        logTestStep("Deleting order");
//
//        logApiCall("DELETE", "/store/order/" + testOrderId, null);
//        Response response = storeService.deleteOrder(testOrderId);
//        logApiResponse(response);
//
//        response.then().statusCode(200);
//
//        logTestStep("Verifying order is deleted");
//        Response getResponse = storeService.getOrderById(testOrderId);
//        getResponse.then().statusCode(404);
//
//        logSuccess("Order deleted successfully");
//    }
//
//    @Test
//    @DisplayName("Should return 404 for non-existent order")
//    void testGetNonExistentOrder() {
//        logTestStep("Testing non-existent order retrieval");
//
//        Long nonExistentOrderId = 999999999L;
//        logTestData("Non-existent Order ID", nonExistentOrderId.toString());
//
//        logApiCall("GET", "/store/order/" + nonExistentOrderId, null);
//        Response response = storeService.getOrderById(nonExistentOrderId);
//        logApiResponse(response);
//
//        response.then().statusCode(404);
//
//        logSuccess("Correctly returned 404 for non-existent order");
//    }
//
//    @AfterAll
//    void cleanup() {
//        logTestStep("Cleaning up test data");
//        try {
//            storeService.deleteOrder(testOrderId);
//            logSuccess("Cleanup completed successfully");
//        } catch (Exception e) {
//            logError("Cleanup failed: " + e.getMessage());
//        }
//    }
//}
//package petstore.tests;
//
//
//import io.restassured.response.Response;
//import org.junit.jupiter.api.*;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
//import petstore.PetStoreService.StoreService;
//import petstore.base.BaseTest;
//import petstore.config.TestConfig;
//import petstore.utils.TestDataGenerator;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringJUnitConfig(TestConfig.class)
//@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
//public class StoreAPITest extends BaseTest {
//
//    @Autowired
//    private StoreService storeService;
//
//    private Long testOrderId;
//    private Long testPetId;
//    private Order testOrder;
//
//    @BeforeAll
//    void setupTestData() {
//        testOrderId = TestDataGenerator.generateRandomId();
//        testPetId = TestDataGenerator.generateRandomId();
//        testOrder  = (Order) TestDataGenerator.createDefaultOrder(testOrderId ,testPetId);
//
//        logTestStep("Test data setup completed");
//        logTestData("Test Order ID", testOrderId.toString());
//        logTestData("Test Pet ID", testPetId.toString());
//        logTestData("Test Order", testOrder.toString());
//    }
//
//    @Test
//    @org.junit.jupiter.api.Order(1)
//    @DisplayName("Should place an order for a pet")
//    void testPlaceOrder() {
//        logTestStep("Placing order for pet");
//
//        logApiCall("POST", "/store/order", testOrder);
//        Response response = storeService.placeOrder((petstore.model.store.Order) testOrder);
//        logApiResponse(response);
//
//        response.then().statusCode(200);
//
//        Order createdOrder = response.as(Order.class);
//
//        logValidation("Verify order ID matches");
//        assertEquals(testOrder.getID(), createdOrder.getId());
//
//        logValidation("Verify pet ID matches");
//        assertEquals(testOrder.getPetId(), createdOrder.getPetId());
//
//        logValidation("Verify order status is 'placed'");
//        assertEquals("placed", createdOrder.getStatus());
//
//        logValidation("Verify complete flag is true");
//        assertTrue(createdOrder.getComplete());
//
//        logSuccess("Order placed successfully");
//    }
//
//    @Test
//    @org.junit.jupiter.api.Order(2)
//    @DisplayName("Should retrieve order by ID")
//    void testGetOrderById() {
//        logTestStep("Retrieving order by ID");
//
//        logApiCall("GET", "/store/order/" + testOrderId, null);
//        Response response = storeService.getOrderById(testOrderId);
//        logApiResponse(response);
//
//        response.then().statusCode(200);
//
//        Order retrievedOrder = response.as(Order.class);
//
//        logValidation("Verify retrieved order ID matches");
//        assertEquals(testOrder.getId(), retrievedOrder.getId());
//
//        logValidation("Verify retrieved pet ID matches");
//        assertEquals(testOrder.getPetId(), retrievedOrder.getPetId());
//
//        logValidation("Verify order quantity matches");
//        assertEquals(testOrder.getQuantity(), retrievedOrder.getQuantity());
//
//        logSuccess("Order retrieved successfully");
//    }
//
//    @Test
//    @org.junit.jupiter.api.Order(3)
//    @DisplayName("Should get store inventory")
//    void testGetInventory() {
//        logTestStep("Retrieving store inventory");
//
//        logApiCall("GET", "/store/inventory", null);
//        Response response = storeService.getInventory();
//        logApiResponse(response);
//
//        response.then().statusCode(200);
//
//        com.petstore.model.store.Inventory inventory = response.as(com.petstore.model.store.Inventory.class);
//
//        logValidation("Verify inventory is not null");
//        assertNotNull(inventory, "Inventory should not be null");
//
//        logValidation("Verify inventory contains data");
//        assertTrue(inventory.getInventoryMap().size() > 0, "Inventory should contain data");
//
//        logTestData("Inventory Size", String.valueOf(inventory.getInventoryMap().size()));
//        logTestData("Available Pets", String.valueOf(inventory.getQuantity("available")));
//        logTestData("Pending Pets", String.valueOf(inventory.getQuantity("pending")));
//        logTestData("Sold Pets", String.valueOf(inventory.getQuantity("sold")));
//
//        logSuccess("Inventory retrieved successfully");
//    }
//
//    @Test
//    @org.junit.jupiter.api.Order(4)
//    @DisplayName("Should delete an order")
//    void testDeleteOrder() {
//        logTestStep("Deleting order");
//
//        logApiCall("DELETE", "/store/order/" + testOrderId, null);
//        Response response = storeService.deleteOrder(testOrderId);
//        logApiResponse(response);
//
//        response.then().statusCode(200);
//
//        logTestStep("Verifying order is deleted");
//        Response getResponse = storeService.getOrderById(testOrderId);
//        getResponse.then().statusCode(404);
//
//        logSuccess("Order deleted successfully");
//    }
//
//    @Test
//    @DisplayName("Should return 404 for non-existent order")
//    void testGetNonExistentOrder() {
//        logTestStep("Testing non-existent order retrieval");
//
//        Long nonExistentOrderId = 999999999L;
//        logTestData("Non-existent Order ID", nonExistentOrderId.toString());
//
//        logApiCall("GET", "/store/order/" + nonExistentOrderId, null);
//        Response response = storeService.getOrderById(nonExistentOrderId);
//        logApiResponse(response);
//
//        response.then().statusCode(404);
//
//        logSuccess("Correctly returned 404 for non-existent order");
//    }
//
//    @AfterAll
//    void cleanup() {
//        logTestStep("Cleaning up test data");
//        try {
//            storeService.deleteOrder(testOrderId);
//            logSuccess("Cleanup completed successfully");
//        } catch (Exception e) {
//            logError("Cleanup failed: " + e.getMessage());
//        }
//    }
//}
