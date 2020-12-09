package by.mybrik.service;

import by.mybrik.domain.entities.StandardOrder;

import java.util.List;
import java.util.Optional;

public interface StandardOrderService {

  StandardOrder save(StandardOrder order);

  List<StandardOrder> findAll();

  StandardOrder findById(Long id);

  Optional<StandardOrder> findOne(Long id);

  StandardOrder update(StandardOrder order);

  Long delete(StandardOrder order);
}
