package by.mybrik.controllers;

import by.mybrik.domain.Goods;
import by.mybrik.service.GoodsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("rest/goods")
@RequiredArgsConstructor
public class GoodsRestController {

    public final GoodsService goodsService;

    // http://localhost:8080/rest/goods
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Goods> findAllGoods(){
        return goodsService.findAll();
    }

    // http://localhost:8080/rest/goods/6
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Goods findProductById(@PathVariable("id") Long id){
        return goodsService.findById(id);
    }



}
