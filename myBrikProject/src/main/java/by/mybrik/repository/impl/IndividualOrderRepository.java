package by.mybrik.repository.impl;

import by.mybrik.domain.IndividualOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IndividualOrderRepository extends JpaRepository<IndividualOrder, Long> {

  List<IndividualOrder> findAllByUserId(Long id);

  @Query(
      value =
          "select sum(orders.totalPrice) from IndividualOrder orders where orders.userId = :userId")
  Double findSumOfAllIndividualOrdersFromUser(Long userId);

  @Query(value = "select sum(orders.totalPrice) from IndividualOrder orders")
  Double calculateTotalSumOfOrders();
}
