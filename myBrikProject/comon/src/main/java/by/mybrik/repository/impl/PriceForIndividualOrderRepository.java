package by.mybrik.repository.impl;

import by.mybrik.domain.PriceForIndividualOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PriceForIndividualOrderRepository
    extends JpaRepository<PriceForIndividualOrder, Long> {

  PriceForIndividualOrder findPriceForIndividualOrderByProductType(String type);

  Boolean existsByProductType(String type);

  List<PriceForIndividualOrder> findAllByIsDeletedIsFalse();
}
