package by.mybrik.service.newImplementation;

import by.mybrik.domain.entities.Goods;

import java.util.List;
import java.util.Optional;

public interface GoodsSer {

    Goods save(Goods product);

    List<Goods> findAll();

    Goods findById(Long id);

    Optional<Goods> findOne(Long id);

    Goods update(Goods product);

    Long delete(Goods product);

}
