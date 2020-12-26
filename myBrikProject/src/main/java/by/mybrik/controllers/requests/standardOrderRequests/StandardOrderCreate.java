package by.mybrik.controllers.requests.standardOrderRequests;

import by.mybrik.domain.OrderStatus;
import lombok.Data;

@Data
public class StandardOrderCreate {

    private long goodId;

    private  long userId;

    private int quantity;

    private double totalPrice;

}
