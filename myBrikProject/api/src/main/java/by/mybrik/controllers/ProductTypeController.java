package by.mybrik.controllers;

import by.mybrik.controllers.requests.productTypeRequests.ProductTypeCreate;
import by.mybrik.controllers.requests.productTypeRequests.ProductTypeUpdate;
import by.mybrik.domain.ProductType;
import by.mybrik.exceptions.EntityNotFoundException;
import by.mybrik.repository.impl.ProductTypeRepository;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

  public final ConversionService conversionService;

  @ApiOperation(value = "Endpoint for getting a list of all product types")
  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<ProductType> findListOfAllProductTypes() {
    return productTypeRepository.findAll();
  }

  @ApiOperation(value = "Endpoint for getting a product type specified by id")
  @GetMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public Optional<ProductType> findProductTypeById(@PathVariable("id") Long id) {
    if (!productTypeRepository.existsById(id)) {
      throw new EntityNotFoundException(String.format("There is no product with id = %d", id));
    }
    return productTypeRepository.findById(id);
  }

  @ApiOperation(value = "Endpoint for deleting product type from database")
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
      throw new EntityNotFoundException(String.format("There is no product with id = %d", id));
    }
    productTypeRepository.deleteById(id);
    return productTypeRepository.findAll();
  }

  @ApiOperation(value = "Endpoint for adding a new product type")
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

    ProductType newType = conversionService.convert(request, ProductType.class);

    return productTypeRepository.save(newType);
  }

  @ApiOperation(value = "Endpoint for updating information about a product type")
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

    request.setId(id);
    ProductType updateType = conversionService.convert(request, ProductType.class);

    return productTypeRepository.save(updateType);
  }

  @ApiOperation(value = "Endpoint for getting a specified product type")
  @GetMapping("/product_type")
  @ResponseStatus(HttpStatus.OK)
  public ProductType findProductByType(@RequestParam String type) {
    return productTypeRepository.findByProductType(type);
  }

  @ApiOperation(
      value =
          "Endpoint for  changing status availability for product type: "
              + "if product type is available - isDeleted should be put on false, "
              + "if product type is not available - isDeleted should be put on true.")
  @Secured("ROLE_ADMIN")
  @ApiImplicitParams(
      @ApiImplicitParam(
          name = "X-Auth-Token",
          defaultValue = "token",
          required = true,
          paramType = "header",
          dataType = "String"))
  @PostMapping("/change_status")
  public ProductType changeStatus(@RequestParam String productType, @RequestParam Boolean status) {

    ProductType product = productTypeRepository.findByProductType(productType);

    product.setDeleted(status);
    product.setChanged(new Timestamp(System.currentTimeMillis()));

    return productTypeRepository.save(product);
  }

  @ApiOperation(value = "Endpoint for getting a list of all available product types")
  @GetMapping("/available_product_types")
  public ResponseEntity<List<ProductType>> findAllAvailableProductTypes() {
    return new ResponseEntity<>(productTypeRepository.findAllByIsDeletedIsFalse(), HttpStatus.OK);
  }
}
