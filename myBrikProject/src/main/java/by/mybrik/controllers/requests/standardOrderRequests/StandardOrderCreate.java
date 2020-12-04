package by.mybrik.controllers.requests.standardOrderRequests;

import lombok.Data;

@Data
public class StandardOrderCreate {

    private long goodId;

    private  long userId;

    private int quantity;

    private double totalPrice;

    private String orderStatus;

}
