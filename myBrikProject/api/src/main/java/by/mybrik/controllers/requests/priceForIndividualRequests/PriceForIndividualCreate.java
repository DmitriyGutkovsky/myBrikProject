package by.mybrik.controllers.requests.priceForIndividualRequests;

import lombok.Data;

@Data
public class PriceForIndividualCreate {

  private String productType;

  private double price;

  private boolean isDeleted;
}
