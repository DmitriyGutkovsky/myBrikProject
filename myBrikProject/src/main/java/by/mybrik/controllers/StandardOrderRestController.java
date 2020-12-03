package by.mybrik.controllers;

import by.mybrik.controllers.requests.StandardOrderRequests.StandardOrderCreateRequest;
import by.mybrik.domain.StandardOrder;
import by.mybrik.service.StandardOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping("rest/standardorder")
@RequiredArgsConstructor
public class StandardOrderRestController {

    public final StandardOrderService orderService;

    // http://localhost:8080/rest/standardorder
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<StandardOrder> getListOfAllStandardOrders(){
        return orderService.findAll();
    }

    // http://localhost:8080/rest/standardorder/1
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public StandardOrder getStandardOrderById(@PathVariable Long id){
        return orderService.findById(id);
    }

    // http://localhost:8080/rest/standardorder/1
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<StandardOrder>  deleteStandardOrder(@PathVariable Long id){
        StandardOrder oderFordelete = orderService.findById(id);
        orderService.delete(oderFordelete);
        return orderService.findAll();
    }

    /*
    http://localhost:8080/rest/standardorder
    {
        "goodId": 6,
        "userId": 10,
        "quantity": 12,
        "totalPrice": 12.0,
        "orderStatus": "in progress",
    }
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public StandardOrder createStandardOrder(@RequestBody StandardOrderCreateRequest request) {

        StandardOrder order = new StandardOrder();

        order.setGoodId(request.getGoodId());
        order.setUserId(request.getUserId());
        order.setQuantity(request.getQuantity());
        order.setTotalPrice(request.getTotalPrice());
        order.setOrderStatus(request.getOrderStatus());

        return orderService.save(order);
    }

    /*
    http://localhost:8080/rest/standardorder/4
    {
            "goodId": 6,
            "userId": 10,
            "quantity": 115,
            "totalPrice": 1200.0,
            "orderStatus": "in progress",
    }
     */
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public StandardOrder updateStandardOrder(@PathVariable Long id, @RequestBody StandardOrderCreateRequest request) {

        StandardOrder updateOrder = orderService.findById(id);

        updateOrder.setGoodId(request.getGoodId());
        updateOrder.setUserId(request.getUserId());
        updateOrder.setQuantity(request.getQuantity());
        updateOrder.setTotalPrice(request.getTotalPrice());
        updateOrder.setOrderStatus(request.getOrderStatus());
        updateOrder.setChanged(new Timestamp(System.currentTimeMillis()));

        return orderService.update(updateOrder);
    }
}