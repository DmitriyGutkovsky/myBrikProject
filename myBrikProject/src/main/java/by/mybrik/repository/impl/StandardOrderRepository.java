package by.mybrik.repository.impl;

import by.mybrik.domain.StandardOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StandardOrderRepository extends JpaRepository<StandardOrder, Long> {

    List<StandardOrder> findAllByUserId(Long id);

    @Query(value = "select sum(orders.totalPrice) from StandardOrder orders where orders.userId = :userId")
    Double findSumOfAllStandardOrdersFromUser(Long userId);
}
