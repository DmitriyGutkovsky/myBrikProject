package by.mybrik.controllers;

import by.mybrik.domain.IndividualOrder;
import by.mybrik.domain.StandardOrder;
import by.mybrik.service.IndividualOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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


}
