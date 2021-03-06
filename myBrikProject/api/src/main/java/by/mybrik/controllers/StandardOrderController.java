package by.mybrik.controllers;

import by.mybrik.controllers.requests.standardOrderRequests.StandardOrderCreate;
import by.mybrik.controllers.requests.standardOrderRequests.StandardOrderUpdate;
import by.mybrik.domain.OrderStatus;
import by.mybrik.domain.StandardOrder;
import by.mybrik.exceptions.EntityNotFoundException;
import by.mybrik.repository.impl.GoodsRepository;
import by.mybrik.repository.impl.StandardOrderRepository;
import by.mybrik.repository.impl.UsersRepository;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
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

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/new/rest/standardorder")
@RequiredArgsConstructor
public class StandardOrderController {

  public final StandardOrderRepository standardOrderRepository;

  public final GoodsRepository goodsRepository;

  public final UsersRepository usersRepository;

  public final ConversionService conversionService;

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
    if (!standardOrderRepository.existsById(id)) {
      throw new EntityNotFoundException(String.format("There is no order with id = %d", id));
    }
    return standardOrderRepository.findById(id);
  }

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
      throw new EntityNotFoundException(String.format("There is no order with id = %d", id));
    }
    standardOrderRepository.deleteById(id);
    return standardOrderRepository.findAll();
  }

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

    StandardOrder order = conversionService.convert(request, StandardOrder.class);

    return standardOrderRepository.save(order);
  }

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
      @RequestBody StandardOrderUpdate request,
      @RequestParam OrderStatus orderStatus) {

    request.setId(id);
    request.setOrderStatus(orderStatus);

    StandardOrder updateOrder = conversionService.convert(request, StandardOrder.class);

    return standardOrderRepository.save(updateOrder);
  }

  @ApiOperation(value = "Endpoint for getting a list of all standard orders for specified user")
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
  public List<StandardOrder> getListOfAllStandardOrdersByUser(@RequestParam Long id) {
    if (!usersRepository.existsById(id)) {
      throw new EntityNotFoundException("There is no such user, please check again");
    }
    return standardOrderRepository.findAllByUserId(id);
  }

  @ApiOperation(value = "Endpoint for calculating a sum of all orders from one user")
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
      throw new EntityNotFoundException("There is no such user, please check again");
    }
    return standardOrderRepository.findSumOfAllStandardOrdersFromUser(id);
  }

  @ApiOperation(value = "Endpoint for calculating a sum of all orders")
  @Secured("ROLE_ADMIN")
  @ApiImplicitParams(
      @ApiImplicitParam(
          name = "X-Auth-Token",
          defaultValue = "token",
          required = true,
          paramType = "header",
          dataType = "String"))
  @GetMapping("/calculate_total_sum_for_standard_orders")
  @ResponseStatus(HttpStatus.OK)
  public Double calculateSumOfAllOrders() {
    return standardOrderRepository.calculateTotalSumOfOrders();
  }
}
