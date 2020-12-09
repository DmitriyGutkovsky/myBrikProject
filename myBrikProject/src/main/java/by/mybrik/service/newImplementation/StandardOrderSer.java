package by.mybrik.service.newImplementation;

import by.mybrik.domain.entities.StandardOrder;

import java.util.List;
import java.util.Optional;

public interface StandardOrderSer {

  StandardOrder save(StandardOrder order);

  List<StandardOrder> findAll();

  StandardOrder findById(Long id);

  Optional<StandardOrder> findOne(Long id);

  StandardOrder update(StandardOrder order);

  Long delete(StandardOrder order);
}
