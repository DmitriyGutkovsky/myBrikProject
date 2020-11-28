package by.mybrik.controllers;

import by.mybrik.domain.ProductType;
import by.mybrik.service.ProductTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/producttype")
@RequiredArgsConstructor
public class ProductTypeRestController {

    public final ProductTypeService typeService;

    // http://localhost:8080/rest/producttype
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductType> getListOfAllProductTypes(){
        return typeService.findAll();
    }


    // http://localhost:8080/rest/producttype/4
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProductType getProductTypeById(@PathVariable("id") Long id){
        return typeService.findById(id);
    }

    // http://localhost:8080/rest/producttype/4
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<ProductType> deleteTypeById(@PathVariable Long id){
        ProductType deletedType = typeService.findById(id);
        typeService.delete(deletedType);
        return typeService.findAll();
    }


}
