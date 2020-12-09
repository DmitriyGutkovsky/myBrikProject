package by.mybrik.service.newImplementation.impl;

import by.mybrik.domain.entities.PriceForIndividualOrder;
import by.mybrik.repository.newImplementation.PriceForIndividualOrderRep;
import by.mybrik.service.newImplementation.PriceForIndividualOrderSer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PriceForIndividualOrderSerImpl implements PriceForIndividualOrderSer {

  private final PriceForIndividualOrderRep priceForIndividualOrderRepository;

  @Override
  public PriceForIndividualOrder save(PriceForIndividualOrder price) {
    return priceForIndividualOrderRepository.save(price);
  }

  @Override
  public List<PriceForIndividualOrder> findAll() {
    return priceForIndividualOrderRepository.findAll();
  }

  @Override
  public PriceForIndividualOrder findById(Long id) {
    return priceForIndividualOrderRepository.findById(id);
  }

  @Override
  public Optional<PriceForIndividualOrder> findOne(Long id) {
    return priceForIndividualOrderRepository.findOne(id);
  }

  @Override
  public PriceForIndividualOrder update(PriceForIndividualOrder price) {
    return priceForIndividualOrderRepository.update(price);
  }

  @Override
  public Long delete(PriceForIndividualOrder price) {
    return priceForIndividualOrderRepository.delete(price);
  }
}
