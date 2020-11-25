package by.mybrik.controllers;

import by.mybrik.controllers.requests.goodsRequests.GoodsCreateRequest;
import by.mybrik.controllers.requests.goodsRequests.GoodsUpdateRequest;
import by.mybrik.domain.Goods;
import by.mybrik.service.GoodsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping("rest/goods")
@RequiredArgsConstructor
public class GoodsRestController {

    public final GoodsService goodsService;

    // http://localhost:8080/rest/goods
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Goods> findAllGoods(){
        return goodsService.findAll();
    }

    // http://localhost:8080/rest/goods/6
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Goods findProductById(@PathVariable("id") Long id){
        return goodsService.findById(id);
    }

    // http://localhost:8080/rest/goods/8
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<Goods> deleteProduct(@PathVariable Long id){
        Goods productForDeleting = goodsService.findById(id);
        goodsService.delete(productForDeleting);
        return goodsService.findAll();
    }

    /*
    POST + http://localhost:8080/rest/goods
    {
    "orderCode": "someOrderCode10",
    "name": "cap",
    "photo": "linkToPhoto",
    "gender": "Male",
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
    public Goods createProduct(@RequestBody GoodsCreateRequest createRequest){

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


    /*
    PUT + http://localhost:8080/rest/goods/10
    {
    "orderCode": "someOrderCode12",
    "name": "capBig",
    "photo": "linkToPhoto",
    "gender": "Male",
    "size": "52",
    "color": "red",
    "description": "summer cap",
    "price": 15.0,
    "quantity": 15,
    "category": "caps",
    "deleted": false
    }
     */
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Goods updateProduct (@PathVariable Long id, @RequestBody GoodsUpdateRequest request){

        Goods product = goodsService.findById(id);

        product.setOrderCode(request.getOrderCode());
        product.setName(request.getName());
        product.setPhoto(request.getPhoto());
        product.setGender(request.getGender());
        product.setSize(request.getSize());
        product.setColor(request.getColor());
        product.setDescription(request.getDescription());
        product.setDeleted(request.isDeleted());
        product.setPrice(request.getPrice());
        product.setQuantity(request.getQuantity());
        product.setCategory(request.getCategory());
        product.setChanged(new Timestamp(System.currentTimeMillis()));

        return goodsService.update(product);
    }
}