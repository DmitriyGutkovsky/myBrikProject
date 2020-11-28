package by.mybrik.controllers;

import by.mybrik.controllers.requests.productTypeRequests.ProductTypeCreateRequest;
import by.mybrik.controllers.requests.productTypeRequests.ProductTypeUpdateRequest;
import by.mybrik.domain.ProductType;
import by.mybrik.service.ProductTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
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

    /*
    http://localhost:8080/rest/producttype
    {
        "productType": "test2",
        "photo": "test2",
        "deleted": false
    }
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductType addProductType(@RequestBody ProductTypeCreateRequest request){

        ProductType newType = new ProductType();

        newType.setProductType(request.getProductType());
        newType.setPhoto(request.getPhoto());
        newType.setDeleted(request.isDeleted());

        return typeService.save(newType);
    }

    /*
    http://localhost:8080/rest/producttype/5
    {
        "productType": "test5",
        "photo": "test5",
        "deleted": false
    }
     */
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProductType updateProductType(@PathVariable Long id, @RequestBody ProductTypeUpdateRequest request){

        ProductType updateType = typeService.findById(id);

        updateType.setProductType(request.getProductType());
        updateType.setPhoto(request.getPhoto());
        updateType.setDeleted(request.isDeleted());
        updateType.setChanged(new Timestamp(System.currentTimeMillis()));

        return typeService.update(updateType);
    }
}
