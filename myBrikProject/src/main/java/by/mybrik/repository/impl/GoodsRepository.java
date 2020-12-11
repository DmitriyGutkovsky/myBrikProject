package by.mybrik.repository.impl;

import by.mybrik.domain.Goods;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GoodsRepository extends JpaRepository<Goods, Long> {
}
