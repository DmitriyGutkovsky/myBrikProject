package by.mybrik.controllers.requests.productTypeRequests;

import lombok.Data;

@Data
public class ProductTypeCreate {

    private String productType;

    private String photo;

    private boolean isDeleted;

}
