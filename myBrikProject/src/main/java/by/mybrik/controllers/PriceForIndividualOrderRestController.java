package by.mybrik.controllers;

import by.mybrik.domain.PriceForIndividualOrder;
import by.mybrik.domain.ProductType;
import by.mybrik.service.PriceForIndividualOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<PriceForIndividualOrder> deleteIndividualOrderPriceById(@PathVariable Long id){
        PriceForIndividualOrder deletedPrice = priceForIndividualOrderService.findById(id);
        priceForIndividualOrderService.delete(deletedPrice);
        return priceForIndividualOrderService.findAll();
    }




}