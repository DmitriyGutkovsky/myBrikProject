package by.mybrik.controllers.requests.individualOrderRequests;

import lombok.Data;

@Data
public class IndividualOrderCreate {

    private long userId;

    private long textileId;

    private long productTypeId;

    private long priceId;

    private int quantity;

    private double totalPrice;

    private String orderStatus;

}
