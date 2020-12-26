package by.mybrik.repository.impl;

import by.mybrik.domain.IndividualOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IndividualOrderRepository extends JpaRepository<IndividualOrder, Long> {

    List<IndividualOrder> findAllByUserId(Long id);
}
