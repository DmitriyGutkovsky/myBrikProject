package by.mybrik.controllers;

import by.mybrik.domain.ProductType;
import by.mybrik.service.ProductTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rest/producttype")
@RequiredArgsConstructor
public class ProductTypeRestController {

    public final ProductTypeService typeService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductType> getListOfAllProductTypes(){
        return typeService.findAll();
    }



}
