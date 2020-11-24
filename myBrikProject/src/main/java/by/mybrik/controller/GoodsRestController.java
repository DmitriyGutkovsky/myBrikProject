package by.mybrik.controller;

import by.mybrik.domain.Goods;
import by.mybrik.service.GoodsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("rest/goods")
@RequiredArgsConstructor
public class GoodsRestController {

    public final GoodsService goodsService;

    //http://localhost:8080/rest/goods
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Goods> findAllGoods(){
        return goodsService.findAll();
    }



}
