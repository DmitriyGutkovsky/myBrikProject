package by.mybrik.controllers.requests.StandardOrderRequests;

import lombok.Data;

@Data
public class StandardOrderCreateRequest {

    private long goodId;

    private  long userId;

    private int quantity;

    private double totalPrice;

    private String orderStatus;

}
