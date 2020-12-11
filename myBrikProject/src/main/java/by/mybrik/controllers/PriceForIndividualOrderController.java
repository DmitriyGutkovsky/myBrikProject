package by.mybrik.controllers;

import by.mybrik.controllers.requests.priceForIndividualRequests.PriceForIndividualCreate;
import by.mybrik.controllers.requests.priceForIndividualRequests.PriceForIndividualUpdate;
import by.mybrik.domain.PriceForIndividualOrder;
import by.mybrik.repository.impl.PriceForIndividualOrderRepository;
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
import java.util.Optional;

@RestController
@RequestMapping("/new/rest/individualorderprice")
@RequiredArgsConstructor
public class PriceForIndividualOrderController {

  public final PriceForIndividualOrderRepository priceForIndividualOrderRepository;

  // http://localhost:8080/new/rest/individualorderprice
  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<PriceForIndividualOrder> getListOfAllPrices() {
    return priceForIndividualOrderRepository.findAll();
  }

  // http://localhost:8080/new/rest/individualorderprice/3
  @GetMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public Optional<PriceForIndividualOrder> getIndividualOrderPriceById(
      @PathVariable("id") Long id) {
    return priceForIndividualOrderRepository.findById(id);
  }

  // http://localhost:8080/new/rest/individualorderprice/3
  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public List<PriceForIndividualOrder> deleteIndividualOrderPriceById(@PathVariable Long id) {
    if (!priceForIndividualOrderRepository.existsById(id)) {
      // TODO own Exception
      throw new RuntimeException();
    }
    priceForIndividualOrderRepository.deleteById(id);
    return priceForIndividualOrderRepository.findAll();
  }

  /*
  http://localhost:8080/new/rest/individualorderprice
  {
      "productType": "test",
      "price": 12.0,
      "deleted": false
  }
   */
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public PriceForIndividualOrder addPriceForIndividualOrder(
      @RequestBody PriceForIndividualCreate request) {

    PriceForIndividualOrder price = new PriceForIndividualOrder();
    price.setProductType(request.getProductType());
    price.setPrice(request.getPrice());
    price.setDeleted(request.isDeleted());

    return priceForIndividualOrderRepository.save(price);
  }

  /*
  http://localhost:8080/new/rest/individualorderprice/4
  {
      "productType": "test",
      "price": 12.0,
      "deleted": false
  }
  */
  @PutMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public PriceForIndividualOrder updatePriceForIndividualOrder(
      @PathVariable("id") Long id, @RequestBody PriceForIndividualUpdate request) {

    if (!priceForIndividualOrderRepository.existsById(id)) {
      // TODO own Exception
      throw new RuntimeException();
    }

    PriceForIndividualOrder price = priceForIndividualOrderRepository.getOne(id);

    price.setProductType(request.getProductType());
    price.setPrice(request.getPrice());
    price.setDeleted(request.isDeleted());
    price.setChanged(new Timestamp(System.currentTimeMillis()));

    return priceForIndividualOrderRepository.save(price);
  }
}
