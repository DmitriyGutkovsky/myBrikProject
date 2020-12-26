package by.mybrik.repository.impl;

import by.mybrik.domain.StandardOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StandardOrderRepository extends JpaRepository<StandardOrder, Long> {

    List<StandardOrder> findAllByUserId(Long id);
}
