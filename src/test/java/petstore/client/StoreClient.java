package petstore.client;

import io.restassured.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import petstore.PetStoreService.StoreService;
import petstore.model.store.Order;

@Component
public class StoreClient {

    @Autowired
    StoreService storeService;

    public  Order placeOrderAndGetResponse(Order order){
        Response response = storeService.placeOrder(order);
        return  response.then().statusCode(200)
                .extract().as(Order.class);
    }

    public boolean isOderExist(Long orederId){

        Response response = storeService.getOrderById(orederId);
        return  response.getStatusCode() == 200;
    }

    public  void deleteOrderIfExists(Long orderID){

        if(isOderExist(orderID)){
            storeService.deleteOrder(orderID);
        }

    }
}
