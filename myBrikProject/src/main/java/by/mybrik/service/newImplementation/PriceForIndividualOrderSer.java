package by.mybrik.service.newImplementation;

import by.mybrik.domain.entities.PriceForIndividualOrder;

import java.util.List;
import java.util.Optional;

public interface PriceForIndividualOrderSer {

  PriceForIndividualOrder save(PriceForIndividualOrder price);

  List<PriceForIndividualOrder> findAll();

  PriceForIndividualOrder findById(Long id);

  Optional<PriceForIndividualOrder> findOne(Long id);

  PriceForIndividualOrder update(PriceForIndividualOrder price);

  Long delete(PriceForIndividualOrder price);

}
