package by.mybrik.repository.impl;

import by.mybrik.domain.Goods;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GoodsRepository extends JpaRepository<Goods, Long> {

  Goods findByOrderCode(String orderCode);

  List<Goods> findAllByisDeletedIsFalse();

  @Query("select u from Goods u where u.price < :query")
  List<Goods> findAllByPriceLessThanQuery(@Param("query") Double query);

  @Query("select u from Goods u where u.price > :query")
  List<Goods> findAllByPriceMoreThanQuery(@Param("query") Double query);

  @Query("select u from Goods u where u.price = :query")
  List<Goods> findAllByPriceEqualQuery(@Param("query") Double query);
}
