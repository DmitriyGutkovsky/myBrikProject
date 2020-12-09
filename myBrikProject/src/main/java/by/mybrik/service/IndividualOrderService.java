package by.mybrik.service;

import by.mybrik.domain.entities.IndividualOrder;

import java.util.List;
import java.util.Optional;

public interface IndividualOrderService {

  IndividualOrder save(IndividualOrder order);

  List<IndividualOrder> findAll();

  IndividualOrder findById(Long id);

  Optional<IndividualOrder> findOne(Long id);

  IndividualOrder update(IndividualOrder order);

  Long delete(IndividualOrder order);
}
