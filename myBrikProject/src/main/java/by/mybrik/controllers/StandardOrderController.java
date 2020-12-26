package by.mybrik.controllers;

import by.mybrik.controllers.requests.standardOrderRequests.StandardOrderCreate;
import by.mybrik.domain.OrderStatus;
import by.mybrik.domain.StandardOrder;
import by.mybrik.repository.impl.StandardOrderRepository;
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
@RequestMapping("/new/rest/standardorder")
@RequiredArgsConstructor
public class StandardOrderController {

  public final StandardOrderRepository standardOrderRepository;

  // http://localhost:8080/new/rest/standardorder
  @ApiOperation(value = "Endpoint for getting a list of all standard orders")
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
  public List<StandardOrder> getListOfAllStandardOrders() {
    return standardOrderRepository.findAll();
  }

  // http://localhost:8080/new/rest/standardorder/1
  @ApiOperation(value = "Endpoint for getting a standard order by id")
  @Secured({"ROLE_ADMIN", "ROLE_USER"})
  @ApiImplicitParams(
      @ApiImplicitParam(
          name = "X-Auth-Token",
          defaultValue = "token",
          required = true,
          paramType = "header",
          dataType = "String"))
  @GetMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public Optional<StandardOrder> findStandardOrderById(@PathVariable Long id) {
    return standardOrderRepository.findById(id);
  }

  // http://localhost:8080/new/rest/standardorder/1
  @ApiOperation(value = "Endpoint for deleting a standard order by id from database")
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
  public List<StandardOrder> deleteStandardOrder(@PathVariable Long id) {
    if (!standardOrderRepository.existsById(id)) {
      // TODO own Exception
      throw new RuntimeException();
    }
    standardOrderRepository.deleteById(id);
    return standardOrderRepository.findAll();
  }

  /*
  http://localhost:8080/new/rest/standardorder
  {
      "goodId": 6,
      "userId": 10,
      "quantity": 12,
      "totalPrice": 12.0,
      "orderStatus": "SEND"
  }
   */
  @ApiOperation(value = "Endpoint for creating a standard")
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
  public StandardOrder createStandardOrder(@RequestBody StandardOrderCreate request) {

    StandardOrder order = new StandardOrder();

    order.setGoodId(request.getGoodId());
    order.setUserId(request.getUserId());
    order.setQuantity(request.getQuantity());
    order.setTotalPrice(request.getTotalPrice());
    order.setOrderStatus(OrderStatus.SEND);

    return standardOrderRepository.save(order);
  }

  /*
  http://localhost:8080/new/rest/standardorder/4
  {
          "goodId": 6,
          "userId": 10,
          "quantity": 115,
          "totalPrice": 1200.0,
          "orderStatus": "in progress"
  }
   */
  @ApiOperation(value = "Endpoint for updating standard order with specified id")
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
  public StandardOrder updateStandardOrder(
      @PathVariable Long id,
      @RequestBody StandardOrderCreate request,
      @RequestParam OrderStatus orderStatus) {
    if (!standardOrderRepository.existsById(id)) {
      // TODO own Exception
      throw new RuntimeException();
    }

    StandardOrder updateOrder = standardOrderRepository.getOne(id);

    updateOrder.setGoodId(request.getGoodId());
    updateOrder.setUserId(request.getUserId());
    updateOrder.setQuantity(request.getQuantity());
    updateOrder.setTotalPrice(request.getTotalPrice());
    updateOrder.setOrderStatus(orderStatus);
    updateOrder.setChanged(new Timestamp(System.currentTimeMillis()));

    return standardOrderRepository.save(updateOrder);
  }
}
