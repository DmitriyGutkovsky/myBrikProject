package by.mybrik.controllers.requests.goodsRequests;

import lombok.Data;

@Data
public class GoodsCreateRequest {

    private String orderCode;

    private String name;

    private  String photo;

    private String gender;

    private String size;

    private String color;

    private String description;

    private boolean isDeleted;

    private double price;

    private int quantity;

    private String category;

}
