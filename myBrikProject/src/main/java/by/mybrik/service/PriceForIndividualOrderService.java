package by.mybrik.service;

import by.mybrik.domain.PriceForIndividualOrder;

import java.util.List;
import java.util.Optional;

public interface PriceForIndividualOrderService {

  PriceForIndividualOrder save(PriceForIndividualOrder price);

  List<PriceForIndividualOrder> findAll();

  PriceForIndividualOrder findById(Long id);

  Optional<PriceForIndividualOrder> findOne(Long id);

  PriceForIndividualOrder update(PriceForIndividualOrder price);

  Long delete(PriceForIndividualOrder price);

}
