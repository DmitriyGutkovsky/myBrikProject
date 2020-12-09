package by.mybrik.service.impl;

import by.mybrik.domain.entities.IndividualOrder;
import by.mybrik.repository.newImplementation.IndividualOrderRep;
import by.mybrik.service.IndividualOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class IndividualOrderServiceImpl implements IndividualOrderService {

  private final IndividualOrderRep orderRepository;

  @Override
  public IndividualOrder save(IndividualOrder order) {
    return orderRepository.save(order);
  }

  @Override
  public List<IndividualOrder> findAll() {
    return orderRepository.findAll();
  }

  @Override
  public IndividualOrder findById(Long id) {
    return orderRepository.findById(id);
  }

  @Override
  public Optional<IndividualOrder> findOne(Long id) {
    return orderRepository.findOne(id);
  }

  @Override
  public IndividualOrder update(IndividualOrder order) {
    return orderRepository.update(order);
  }

  @Override
  public Long delete(IndividualOrder order) {
    return orderRepository.delete(order);
  }
}
