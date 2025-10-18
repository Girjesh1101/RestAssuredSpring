package petstore.model.store;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Order {

    @JsonProperty("petId")
    private  Long petId;

    @JsonProperty("quantity")
    private int quantity;

    @JsonProperty("shipdate")
    @JsonFormat(shape =  JsonFormat.Shape.STRING , pattern = "yyyy-MM-dd'T'HH:mm.ss.SSS'Z'")
    private LocalDateTime shipDate;

    @JsonProperty("status")
    private  String status;

    @JsonProperty("complete")
    private Boolean complete;



    @JsonProperty("id")
    private  Long id;

    public Order(){}

    Order(Long id, Long petId , int quantity , String status , boolean complete)
    {
        this.id = id;
        this.petId = petId;
        this.quantity = quantity;
        this.shipDate = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPetId() {
        return petId;
    }

    public void setPetId(Long petId) {
        this.petId = petId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public LocalDateTime getDate() {
        return shipDate;
    }

    public void setDate(LocalDateTime shipDate) {
        this.shipDate = shipDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Boolean getComplete() {
        return complete;
    }

    public void setComplete(Boolean complete) {
        this.complete = complete;
    }


}
