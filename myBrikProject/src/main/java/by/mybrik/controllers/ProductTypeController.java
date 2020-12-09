package by.mybrik.controllers;

import by.mybrik.controllers.requests.productTypeRequests.ProductTypeCreate;
import by.mybrik.controllers.requests.productTypeRequests.ProductTypeUpdate;
import by.mybrik.domain.entities.ProductType;
import by.mybrik.service.newImplementation.ProductTypeSer;
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
@RequestMapping("/new/rest/producttype")
@RequiredArgsConstructor
public class ProductTypeController {

  public final ProductTypeSer typeService;

  // http://localhost:8080/new/rest/producttype
  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<ProductType> getListOfAllProductTypes() {
    return typeService.findAll();
  }

  // http://localhost:8080/new/rest/producttype/4
  @GetMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public ProductType getProductTypeById(@PathVariable("id") Long id) {
    return typeService.findById(id);
  }

  // http://localhost:8080/new/rest/producttype/4
  @DeleteMapping("{id}")
  @ResponseStatus(HttpStatus.OK)
  public List<ProductType> deleteTypeById(@PathVariable Long id) {
    ProductType deletedType = typeService.findById(id);
    typeService.delete(deletedType);
    return typeService.findAll();
  }

  /*
  http://localhost:8080/new/rest/producttype
  {
      "productType": "test2Creat",
      "photo": "test2",
      "deleted": false
  }
   */
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public ProductType addProductType(@RequestBody ProductTypeCreate request) {

    ProductType newType = new ProductType();

    newType.setProductType(request.getProductType());
    newType.setPhoto(request.getPhoto());
    newType.setDeleted(request.isDeleted());

    return typeService.save(newType);
  }

  /*
  http://localhost:8080/new/rest/producttype/5
  {
      "productType": "test5Update",
      "photo": "test5",
      "deleted": false
  }
   */
  @PutMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public ProductType updateProductType(
      @PathVariable Long id, @RequestBody ProductTypeUpdate request) {

    ProductType updateType = typeService.findById(id);

    updateType.setProductType(request.getProductType());
    updateType.setPhoto(request.getPhoto());
    updateType.setDeleted(request.isDeleted());
    updateType.setChanged(new Timestamp(System.currentTimeMillis()));

    return typeService.update(updateType);
  }
}
