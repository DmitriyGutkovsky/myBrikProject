package by.mybrik.repository.impl;

import by.mybrik.domain.Goods;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GoodsRepository extends JpaRepository<Goods, Long> {

    Goods findByOrderCode(String orderCode);

    List<Goods> findAllByisDeletedIsFalse();
}
