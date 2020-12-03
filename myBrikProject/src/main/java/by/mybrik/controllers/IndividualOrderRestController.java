package by.mybrik.controllers;

import by.mybrik.controllers.requests.individualOrderRequests.IndividualOrderCreateRequest;
import by.mybrik.domain.IndividualOrder;
import by.mybrik.service.IndividualOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("rest/individualorder")
@RequiredArgsConstructor
public class IndividualOrderRestController {

    public final IndividualOrderService individualOrderService;

    // http://localhost:8080/rest/individualorder
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<IndividualOrder> getListOfAllIndividualOrders(){
        return individualOrderService.findAll();
    }

    // http://localhost:8080/rest/individualorder/1
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public IndividualOrder getIndividualOrderById(@PathVariable Long id){
        return individualOrderService.findById(id);
    }

    // http://localhost:8080/rest/individualorder/1
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<IndividualOrder>  deleteIndividualOrder(@PathVariable Long id){
        IndividualOrder oderFordelete = individualOrderService.findById(id);
        individualOrderService.delete(oderFordelete);
        return individualOrderService.findAll();
    }

    /*
    http://localhost:8080/rest/standardorder

    {
        "userId": 2,
        "textileId": 1,
        "productTypeId": 1,
        "priceId": 1,
        "quantity": 1,
        "totalPrice": 10,
        "orderStatus": "created",
    }
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public IndividualOrder createIndividualOrder(@RequestBody IndividualOrderCreateRequest request) {

        IndividualOrder order = new IndividualOrder();

        order.setUserId(request.getUserId());
        order.setTextileId(request.getTextileId());
        order.setProductTypeId(request.getProductTypeId());
        order.setPriceId(request.getPriceId());
        order.setQuantity(request.getQuantity());
        order.setTotalPrice(request.getTotalPrice());
        order.setOrderStatus(request.getOrderStatus());

        return individualOrderService.save(order);
    }

}
