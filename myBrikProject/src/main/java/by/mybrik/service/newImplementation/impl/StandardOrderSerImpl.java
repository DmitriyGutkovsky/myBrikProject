package by.mybrik.service.newImplementation.impl;

import by.mybrik.domain.entities.StandardOrder;
import by.mybrik.repository.newImplementation.StandardOrderRep;
import by.mybrik.service.newImplementation.StandardOrderSer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StandardOrderSerImpl implements StandardOrderSer {

  private final StandardOrderRep standardOrderRepository;

  @Override
  public StandardOrder save(StandardOrder order) {
    return standardOrderRepository.save(order);
  }

  @Override
  public List<StandardOrder> findAll() {
    return standardOrderRepository.findAll();
  }

  @Override
  public StandardOrder findById(Long id) {
    return standardOrderRepository.findById(id);
  }

  @Override
  public Optional<StandardOrder> findOne(Long id) {
    return standardOrderRepository.findOne(id);
  }

  @Override
  public StandardOrder update(StandardOrder order) {
    return standardOrderRepository.update(order);
  }

  @Override
  public Long delete(StandardOrder order) {
    return standardOrderRepository.delete(order);
  }
}
