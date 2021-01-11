package by.mybrik.controllers;

import by.mybrik.controllers.requests.priceForIndividualRequests.PriceForIndividualCreate;
import by.mybrik.controllers.requests.priceForIndividualRequests.PriceForIndividualUpdate;
import by.mybrik.domain.PriceForIndividualOrder;
import by.mybrik.exceptions.EntityNotFoundException;
import by.mybrik.repository.impl.PriceForIndividualOrderRepository;
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
@RequestMapping("/new/rest/individualorderprice")
@RequiredArgsConstructor
public class PriceForIndividualOrderController {

  public final PriceForIndividualOrderRepository priceForIndividualOrderRepository;

  public final ConversionService conversionService;

  @ApiOperation(value = "Endpoint for getting a list of all prices for individual orders")
  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<PriceForIndividualOrder> getListOfAllPrices() {
    return priceForIndividualOrderRepository.findAll();
  }

  @ApiOperation(value = "Endpoint for getting a price for individual order specified by id")
  @GetMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public Optional<PriceForIndividualOrder> getIndividualOrderPriceById(
      @PathVariable("id") Long id) {
    if (!priceForIndividualOrderRepository.existsById(id)) {
      throw new EntityNotFoundException(
          String.format("There is no such price for individual order with id = %d", id));
    }
    return priceForIndividualOrderRepository.findById(id);
  }

  @ApiOperation(value = "Endpoint for deleting a price for individual order from database")
  @Secured("ROLE_ADMIN")
  @ApiImplicitParams(
      @ApiImplicitParam(
          name = "X-Auth-Token",
          defaultValue = "token",
          required = true,
          paramType = "header",
          dataType = "String"))
  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public List<PriceForIndividualOrder> deleteIndividualOrderPriceById(@PathVariable Long id) {
    if (!priceForIndividualOrderRepository.existsById(id)) {
      throw new EntityNotFoundException(
          String.format("There is no such price for individual order with id = %d", id));
    }
    priceForIndividualOrderRepository.deleteById(id);
    return priceForIndividualOrderRepository.findAll();
  }

  @ApiOperation(value = "Endpoint for adding a price for individual order")
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
  public PriceForIndividualOrder addPriceForIndividualOrder(
      @RequestBody PriceForIndividualCreate request) {

    PriceForIndividualOrder price =
        conversionService.convert(request, PriceForIndividualOrder.class);

    return priceForIndividualOrderRepository.save(price);
  }

  @ApiOperation(value = "Endpoint for updating information about price for individual order")
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
  public PriceForIndividualOrder updatePriceForIndividualOrder(
      @PathVariable("id") Long id, @RequestBody PriceForIndividualUpdate request) {

    request.setId(id);
    PriceForIndividualOrder price =
        conversionService.convert(request, PriceForIndividualOrder.class);

    return priceForIndividualOrderRepository.save(price);
  }

  @ApiOperation(
      value =
          "Endpoint for changing status availability of price for individual order: "
              + "if price is available - isDeleted should be put on false, "
              + "if price is not available - isDeleted should be put on true.")
  @Secured("ROLE_ADMIN")
  @ApiImplicitParams(
      @ApiImplicitParam(
          name = "X-Auth-Token",
          defaultValue = "token",
          required = true,
          paramType = "header",
          dataType = "String"))
  @PostMapping("/change_status")
  public PriceForIndividualOrder changeStatus(
      @RequestParam String productType, @RequestParam Boolean status) {

    PriceForIndividualOrder price =
        priceForIndividualOrderRepository.findPriceForIndividualOrderByProductType(productType);

    price.setDeleted(status);
    price.setChanged(new Timestamp(System.currentTimeMillis()));

    return priceForIndividualOrderRepository.save(price);
  }

  @ApiOperation(
      value =
          "Endpoint for getting a price for individual order specified by product type and active price")
  @GetMapping("/price_by_product_type")
  @ResponseStatus(HttpStatus.OK)
  public PriceForIndividualOrder getIndividualOrderPriceByProductType(@RequestParam String type) {
    if (!priceForIndividualOrderRepository.existsByProductType(type)) {
      throw new EntityNotFoundException("There is no price for such product");
    }

    PriceForIndividualOrder pricebyProductType =
        priceForIndividualOrderRepository.findPriceForIndividualOrderByProductType(type);

    if (pricebyProductType.isDeleted()) {
      throw new EntityNotFoundException("Price for this product is not active");
    }

    return pricebyProductType;
  }

  @ApiOperation(value = "Endpoint for getting a list of all available prices")
  @GetMapping("/available_prices")
  public ResponseEntity<List<PriceForIndividualOrder>> findAllAvailablePrices() {
    return new ResponseEntity<>(
        priceForIndividualOrderRepository.findAllByIsDeletedIsFalse(), HttpStatus.OK);
  }
}
