package by.mybrik.service;

import by.mybrik.domain.Goods;

import java.util.List;
import java.util.Optional;

public interface GoodsService {

    Goods save(Goods product);

    List<Goods> findAll();

    Goods findById(Long id);

    Optional<Goods> findOne(Long id);

    Goods update(Goods object);

    Long delete(Goods object);

}
