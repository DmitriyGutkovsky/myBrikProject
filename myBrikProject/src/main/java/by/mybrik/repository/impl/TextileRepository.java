package by.mybrik.repository.impl;

import by.mybrik.domain.Textile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import springfox.documentation.annotations.Cacheable;

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
    @Query(value = "select * from (select * from m_product_type join l_textile_product_type ltpt on m_product_type.id = ltpt.product_type_id) as m " +
            "join m_textile mt on mt.id = m.textile_id where mt.name = :name", nativeQuery = true)
    List<Textile> findAllByProductTypes(String name);

    @Cacheable("textile")
    boolean existsByName(String name);
}
