package by.mybrik.controllers.newRevision;


import by.mybrik.controllers.requests.goodsRequests.GoodsCreate;
import by.mybrik.domain.entities.Goods;
import by.mybrik.service.newImplementation.GoodsSer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("new/rest/goods")
public class GoodsController {

    public final GoodsSer goodsService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Goods> findAllGoods(){
        return goodsService.findAll();
    }

/*
    POST + http://localhost:8080/new/rest/goods
    {
    "orderCode": "someOrderCode120",
    "name": "cap2",
    "photo": "linkToPhoto",
    "gender": "MALE",
    "size": "52",
    "color": "red",
    "description": "summer cap",
    "price": 15.0,
    "quantity": 15,
    "category": "caps",
    "deleted": false
    }
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Goods createProduct(@RequestBody GoodsCreate createRequest){

        Goods product = new Goods();

        product.setOrderCode(createRequest.getOrderCode());
        product.setName(createRequest.getName());
        product.setPhoto(createRequest.getPhoto());
        product.setGender(createRequest.getGender());
        product.setSize(createRequest.getSize());
        product.setColor(createRequest.getColor());
        product.setDescription(createRequest.getDescription());
        product.setDeleted(createRequest.isDeleted());
        product.setPrice(createRequest.getPrice());
        product.setQuantity(createRequest.getQuantity());
        product.setCategory(createRequest.getCategory());

        return goodsService.save(product);
    }

}
