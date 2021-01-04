package by.mybrik.repository.impl;

import by.mybrik.domain.Goods;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GoodsRepository extends JpaRepository<Goods, Long> {

  @Cacheable("goods")
  Goods findByOrderCode(String orderCode);

  @Cacheable("goods")
  List<Goods> findAllByIsDeletedIsFalse();

  @Cacheable("goods")
  @Query("select u from Goods u where u.price < :query")
  List<Goods> findAllByPriceLessThanQuery(@Param("query") Double query);

  @Cacheable("goods")
  @Query("select u from Goods u where u.price > :query")
  List<Goods> findAllByPriceMoreThanQuery(@Param("query") Double query);

  @Cacheable("goods")
  @Query("select u from Goods u where u.price = :query")
  List<Goods> findAllByPriceEqualQuery(@Param("query") Double query);

  @Cacheable("goods")
  List<Goods> findAllBySizeBefore(Integer query);

  @Cacheable("goods")
  List<Goods> findAllBySizeAfter(Integer query);

  @Cacheable("goods")
  List<Goods> findAllBySize(Integer size);
}
