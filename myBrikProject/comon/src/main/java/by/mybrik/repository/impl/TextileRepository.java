package by.mybrik.repository.impl;

import by.mybrik.domain.Textile;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface TextileRepository extends JpaRepository<Textile, Long> {

  @Cacheable("textile")
  Textile findByCode(String code);

  @Cacheable("textile")
  List<Textile> findAllByIsDeletedIsFalse();

  @Cacheable("textile")
  Textile findByName(String name);

  @Cacheable("textile")
  List<Textile> findAllByColor(String color);

  @Cacheable("textile")
  @Query(
      value =
          "select * from (select * from m_product_type join l_textile_product_type ltpt on m_product_type.id = ltpt.product_type_id) as m "
              + "join m_textile mt on mt.id = m.textile_id where mt.name = :name",
      nativeQuery = true)
  List<Textile> findAllByProductTypes(String name);

  @Cacheable("textile")
  boolean existsByName(String name);

  @Transactional
  @Modifying(flushAutomatically = true)
  @Query(
      value =
          "insert into l_textile_product_type(textile_id, product_type_id) values (:textile_id, :product_type_id)",
      nativeQuery = true)
  void addingPossibleProductType(
      @Param("textile_id") Long textileId, @Param("product_type_id") Long productTypeId);

  @Transactional
  @Modifying(flushAutomatically = true)
  @Query(
      value =
          "delete from l_textile_product_type where textile_id = :textile_id and product_type_id = :product_type_id",
      nativeQuery = true)
  void deletePossibleProductType(
      @Param("textile_id") Long textileId, @Param("product_type_id") Long productTypeId);
}
