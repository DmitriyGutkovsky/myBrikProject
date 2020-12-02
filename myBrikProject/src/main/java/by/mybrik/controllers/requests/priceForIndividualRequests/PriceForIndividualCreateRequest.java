package by.mybrik.controllers.requests.priceForIndividualRequests;

import lombok.Data;

@Data
public class PriceForIndividualCreateRequest {

    private String productType;

    private double price;

    private boolean isDeleted;
}
