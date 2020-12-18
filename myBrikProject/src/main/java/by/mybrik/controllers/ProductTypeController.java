package by.mybrik.controllers;

import by.mybrik.controllers.requests.productTypeRequests.ProductTypeCreate;
import by.mybrik.controllers.requests.productTypeRequests.ProductTypeUpdate;
import by.mybrik.domain.ProductType;
import by.mybrik.repository.impl.ProductTypeRepository;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
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
import java.util.Optional;

@RestController
@RequestMapping("/new/rest/producttype")
@RequiredArgsConstructor
public class ProductTypeController {

  public final ProductTypeRepository productTypeRepository;

  // http://localhost:8080/new/rest/producttype
  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<ProductType> getListOfAllProductTypes() {
    return productTypeRepository.findAll();
  }

  // http://localhost:8080/new/rest/producttype/4
  @GetMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public Optional<ProductType> getProductTypeById(@PathVariable("id") Long id) {
    return productTypeRepository.findById(id);
  }

  // http://localhost:8080/new/rest/producttype/4
  @Secured("ROLE_ADMIN")
  @ApiImplicitParams(
      @ApiImplicitParam(
          name = "X-Auth-Token",
          defaultValue = "token",
          required = true,
          paramType = "header",
          dataType = "String"))
  @DeleteMapping("{id}")
  @ResponseStatus(HttpStatus.OK)
  public List<ProductType> deleteTypeById(@PathVariable Long id) {
    if (!productTypeRepository.existsById(id)) {
      // TODO own Exception
      throw new RuntimeException();
    }
    productTypeRepository.deleteById(id);
    return productTypeRepository.findAll();
  }

  /*
  http://localhost:8080/new/rest/producttype
  {
      "productType": "test2Creat",
      "photo": "test2",
      "deleted": false
  }
   */
  @Secured("ROLE_ADMIN")
  @ApiImplicitParams(
      @ApiImplicitParam(
          name = "X-Auth-Token",
          defaultValue = "token",
          required = true,
          paramType = "header",
          dataType = "String"))
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public ProductType addProductType(@RequestBody ProductTypeCreate request) {

    ProductType newType = new ProductType();

    newType.setProductType(request.getProductType());
    newType.setPhoto(request.getPhoto());
    newType.setDeleted(request.isDeleted());

    return productTypeRepository.save(newType);
  }

  /*
  http://localhost:8080/new/rest/producttype/5
  {
      "productType": "test5Update",
      "photo": "test5",
      "deleted": false
  }
   */
  @Secured("ROLE_ADMIN")
  @ApiImplicitParams(
      @ApiImplicitParam(
          name = "X-Auth-Token",
          defaultValue = "token",
          required = true,
          paramType = "header",
          dataType = "String"))
  @PutMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public ProductType updateProductType(
      @PathVariable Long id, @RequestBody ProductTypeUpdate request) {

    if (!productTypeRepository.existsById(id)) {
      // TODO own Exception
      throw new RuntimeException();
    }

    ProductType updateType = productTypeRepository.getOne(id);

    updateType.setProductType(request.getProductType());
    updateType.setPhoto(request.getPhoto());
    updateType.setDeleted(request.isDeleted());
    updateType.setChanged(new Timestamp(System.currentTimeMillis()));

    return productTypeRepository.save(updateType);
  }
}
