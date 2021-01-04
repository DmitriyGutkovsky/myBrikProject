package by.mybrik.controllers.requests.goodsRequests;

import by.mybrik.domain.Gender;
import lombok.Data;

@Data
public class GoodsCreate {

    private String orderCode;

    private String name;

    private  String photo;

    private Gender gender;

    private Integer size;

    private String color;

    private String description;

    private boolean isDeleted;

    private double price;

    private int quantity;

    private String category;

}
