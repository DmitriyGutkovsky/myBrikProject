package by.mybrik.controllers;

import by.mybrik.controllers.requests.priceForIndividualRequests.PriceForIndividualCreateRequest;
import by.mybrik.controllers.requests.priceForIndividualRequests.PriceForIndividualUpdateRequest;
import by.mybrik.domain.PriceForIndividualOrder;
import by.mybrik.service.PriceForIndividualOrderService;
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
@RequestMapping("/rest/individualorderprice")
@RequiredArgsConstructor
public class PriceForIndividualOrderRestController {

    public final PriceForIndividualOrderService priceForIndividualOrderService;

    // http://localhost:8080/rest/individualorderprice
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<PriceForIndividualOrder> getListOfAllPrices(){
        return priceForIndividualOrderService.findAll();
    }

    // http://localhost:8080/rest/individualorderprice/3
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PriceForIndividualOrder getIndividualOrderPriceById(@PathVariable("id") Long id){
        return priceForIndividualOrderService.findById(id);
    }

    // http://localhost:8080/rest/individualorderprice/3
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<PriceForIndividualOrder> deleteIndividualOrderPriceById(@PathVariable Long id){
        PriceForIndividualOrder deletedPrice = priceForIndividualOrderService.findById(id);
        priceForIndividualOrderService.delete(deletedPrice);
        return priceForIndividualOrderService.findAll();
    }

    /*
    http://localhost:8080/rest/individualorderprice
    {
        "productType": "test",
        "price": 12.0,
        "deleted": false
    }
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PriceForIndividualOrder addPriceForIndividualOrder(@RequestBody PriceForIndividualCreateRequest request ){

        PriceForIndividualOrder price = new PriceForIndividualOrder();
        price.setProductType(request.getProductType());
        price.setPrice(request.getPrice());
        price.setDeleted(request.isDeleted());

        return priceForIndividualOrderService.save(price);
    }

    /*
    http://localhost:8080/rest/individualorderprice/4
    {
        "productType": "test",
        "price": 12.0,
        "deleted": false
    }
    */
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PriceForIndividualOrder updatePriceForIndividualOrder(@PathVariable ("id") Long id, @RequestBody PriceForIndividualUpdateRequest request ){

        PriceForIndividualOrder price = priceForIndividualOrderService.findById(id);
        price.setProductType(request.getProductType());
        price.setPrice(request.getPrice());
        price.setDeleted(request.isDeleted());
        price.setChanged(new Timestamp(System.currentTimeMillis()));

        return priceForIndividualOrderService.update(price);
    }
}
