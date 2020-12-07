package by.mybrik.controllers.newRevision;


import by.mybrik.domain.entities.Goods;
import by.mybrik.service.newImplementation.GoodsSer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("new/rest/goods")
public class GoodsController {

    public final GoodsSer goodsService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Goods> findAllGoods(){
        return goodsService.findAll();
    }



}
