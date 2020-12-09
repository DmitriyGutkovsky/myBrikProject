package by.mybrik.service.impl;

import by.mybrik.domain.StandardOrder;
import by.mybrik.repository.StandardOrderRepository;
import by.mybrik.service.StandardOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StandardOrderServiceImpl implements StandardOrderService {

  private final StandardOrderRepository standardOrderRepository;

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
