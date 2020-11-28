package by.mybrik.controllers.requests.productTypeRequests;

import lombok.Data;

@Data
public class ProductTypeCreateRequest {

    private String productType;

    private String photo;

    private boolean isDeleted;

}
