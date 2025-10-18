package petstore.PetStoreService;

import io.restassured.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import petstore.model.store.Order;

import static io.restassured.RestAssured.*;

@Service
public class StoreService {

    private io.restassured.specification.RequestSpecification requestSpecification;

    @Autowired
    public StoreService (io.restassured.specification.RequestSpecification requestSpecification){
        this.requestSpecification = requestSpecification;
    };



    private  final String place_order = "/store/order";
    private  final  String find_purchase_order_by_id = "store/order/{orderId}";
    private  final String delete_orderId = "store/order/{orderId}";
    private  final String store_inventory  ="/store/inventory";

    public Response placeOrder(Order order){

        return given()
                .spec(requestSpecification)
                .body(order)
                .when()
                .post(place_order);
    }

    public  Response getOrderById(Long orderId){

        return  given()
                .spec(requestSpecification)
                .pathParam("orderId",orderId)
                .when()
                .get(find_purchase_order_by_id);
    }

    public Response deleteOrder(Long orderId){

        return  given().spec(requestSpecification)
                .pathParam("orderId", orderId)
                .when()
                .get(delete_orderId);
    }

    public  Response getInventory(){
        return  given()
                .spec(requestSpecification)
                .when()
                .get(store_inventory);
    }

}
