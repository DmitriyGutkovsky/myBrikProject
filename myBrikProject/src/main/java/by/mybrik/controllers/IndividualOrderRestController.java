package by.mybrik.controllers;

import by.mybrik.domain.IndividualOrder;
import by.mybrik.service.IndividualOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
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

}
