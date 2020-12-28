package by.mybrik.controllers;

import by.mybrik.controllers.requests.individualOrderRequests.IndividualOrderCreate;
import by.mybrik.controllers.requests.individualOrderRequests.IndividualOrderUpdate;
import by.mybrik.domain.IndividualOrder;
import by.mybrik.domain.OrderStatus;
import by.mybrik.domain.PriceForIndividualOrder;
import by.mybrik.exceptions.EntityNotFoundException;
import by.mybrik.repository.impl.IndividualOrderRepository;
import by.mybrik.repository.impl.PriceForIndividualOrderRepository;
import by.mybrik.repository.impl.UsersRepository;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/new/rest/individualorder")
@RequiredArgsConstructor
public class IndividualOrderController {

  public final IndividualOrderRepository individualOrderRepository;

  public final PriceForIndividualOrderRepository priceForIndividualOrderRepository;

  public final UsersRepository usersRepository;

  // http://localhost:8080/new/rest/individualorder
  @ApiOperation(value = "Endpoint for getting full list of individual orders")
  @Secured("ROLE_ADMIN")
  @ApiImplicitParams(
      @ApiImplicitParam(
          name = "X-Auth-Token",
          defaultValue = "token",
          required = true,
          paramType = "header",
          dataType = "String"))
  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<IndividualOrder> getListOfAllIndividualOrders() {
    return individualOrderRepository.findAll();
  }

  // http://localhost:8080/new/rest/individualorder/1
  @ApiOperation(value = "Endpoint for getting an individual order by id")
  @Secured({"ROLE_ADMIN", "ROLE_ADMIN"})
  @ApiImplicitParams(
      @ApiImplicitParam(
          name = "X-Auth-Token",
          defaultValue = "token",
          required = true,
          paramType = "header",
          dataType = "String"))
  @GetMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public Optional<IndividualOrder> getIndividualOrderById(@PathVariable Long id) {
    return individualOrderRepository.findById(id);
  }

  // http://localhost:8080/new/rest/individualorder/1
  @ApiOperation(value = "Endpoint for hard deleting an individual order from database by id")
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
  public List<IndividualOrder> deleteIndividualOrder(@PathVariable Long id) {
    if (!individualOrderRepository.existsById(id)) {
      throw new EntityNotFoundException("There is no individual order with id = " + id);
    }
    individualOrderRepository.deleteById(id);
    return individualOrderRepository.findAll();
  }

  /*
   http://localhost:8080/new/rest/individualorder
  {
     "userId": 2,
     "textileId": 1,
     "productTypeId": 1,
     "priceId": 1,
     "quantity": 1,
     "totalPrice": 10,
     "orderStatus": "created"
   }
    */
  @ApiOperation(value = "Endpoint for creating an individual order")
  @Secured({"ROLE_ADMIN", "ROLE_USER"})
  @ApiImplicitParams(
      @ApiImplicitParam(
          name = "X-Auth-Token",
          defaultValue = "token",
          required = true,
          paramType = "header",
          dataType = "String"))
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public IndividualOrder createIndividualOrder(@RequestBody IndividualOrderCreate request) {

    Long priceId = request.getPriceId();

    if (!priceForIndividualOrderRepository.existsById(priceId)) {
      throw new EntityNotFoundException("There is no such price for individual order");
    }

    Optional<PriceForIndividualOrder> individualOrderPrice =
        priceForIndividualOrderRepository.findById(priceId);
    Double pricePerOnePcs = individualOrderPrice.get().getPrice();
    Double totalPrice = pricePerOnePcs * request.getQuantity();

    IndividualOrder order = new IndividualOrder();

    order.setUserId(request.getUserId());
    order.setTextileId(request.getTextileId());
    order.setProductTypeId(request.getProductTypeId());
    order.setPriceId(request.getPriceId());
    order.setQuantity(request.getQuantity());
    order.setTotalPrice(totalPrice);
    order.setOrderStatus(OrderStatus.SEND);

    return individualOrderRepository.save(order);
  }

  /*
  http://localhost:8080/new/rest/individualorder/4

  {
      "userId": 6,
      "textileId": 1,
      "productTypeId": 1,
      "priceId": 1,
      "quantity": 1,
      "totalPrice": 10,
      "orderStatus": "updated"
  }
  */
  @ApiOperation(value = "Endpoint for updating information about individual order")
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
  public IndividualOrder updateIndividualOrder(
      @PathVariable Long id,
      @RequestBody IndividualOrderUpdate request,
      @RequestParam OrderStatus orderStatus) {

    Long priceId = request.getPriceId();

    if (!individualOrderRepository.existsById(id)
        || !priceForIndividualOrderRepository.existsById(priceId)) {
      throw new EntityNotFoundException("There is no such price for individual order");
    }

    Optional<PriceForIndividualOrder> individualOrderPrice =
        priceForIndividualOrderRepository.findById(priceId);
    Double pricePerOnePcs = individualOrderPrice.get().getPrice();
    Double totalPrice = pricePerOnePcs * request.getQuantity();

    IndividualOrder updateOrder = individualOrderRepository.getOne(id);

    updateOrder.setUserId(request.getUserId());
    updateOrder.setTextileId(request.getTextileId());
    updateOrder.setProductTypeId(request.getProductTypeId());
    updateOrder.setPriceId(request.getPriceId());
    updateOrder.setQuantity(request.getQuantity());
    updateOrder.setTotalPrice(totalPrice);
    updateOrder.setOrderStatus(orderStatus);
    updateOrder.setChanged(new Timestamp(System.currentTimeMillis()));

    return individualOrderRepository.save(updateOrder);
  }

  @ApiOperation(value = "Endpoint for getting a list of all individual orders for specified user")
  @Secured("ROLE_ADMIN")
  @ApiImplicitParams(
      @ApiImplicitParam(
          name = "X-Auth-Token",
          defaultValue = "token",
          required = true,
          paramType = "header",
          dataType = "String"))
  @GetMapping("/user_order_list")
  @ResponseStatus(HttpStatus.OK)
  public List<IndividualOrder> getListOfAllStandardOrdersByUser(@RequestParam Long id) {
    if (!usersRepository.existsById(id)) {
      throw new EntityNotFoundException("There is such user, please check again");
    }
    return individualOrderRepository.findAllByUserId(id);
  }

  @ApiOperation(value = "Endpoint for calculating a sum of all individual orders from one user")
  @Secured("ROLE_ADMIN")
  @ApiImplicitParams(
      @ApiImplicitParam(
          name = "X-Auth-Token",
          defaultValue = "token",
          required = true,
          paramType = "header",
          dataType = "String"))
  @GetMapping("/sum_of_orders_from_one_user")
  @ResponseStatus(HttpStatus.OK)
  public Double calculateSumOfOrdersByUser(@RequestParam Long id) {
    if (!usersRepository.existsById(id)) {
      throw new EntityNotFoundException("There is such user, please check again");
    }
    return individualOrderRepository.findSumOfAllIndividualOrdersFromUser(id);
  }

  @ApiOperation(value = "Endpoint for calculating a sum of all individual orders")
  @Secured("ROLE_ADMIN")
  @ApiImplicitParams(
      @ApiImplicitParam(
          name = "X-Auth-Token",
          defaultValue = "token",
          required = true,
          paramType = "header",
          dataType = "String"))
  @GetMapping("/calculate_total_sum_for_individual_orders")
  @ResponseStatus(HttpStatus.OK)
  public Double calculateSumOfAllOrders() {
    return individualOrderRepository.calculateTotalSumOfOrders();
  }
}
