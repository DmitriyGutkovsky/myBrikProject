package by.mybrik.repository.impl;

import by.mybrik.domain.Textile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TextileRepository extends JpaRepository<Textile, Long> {

    Textile findByCode(String code);

    List<Textile> findAllByIsDeletedIsFalse();

    Textile findByName(String name);

    List<Textile> findAllByColor(String color);

    @Query(value = "select * from (select * from m_product_type join m_textile_product_type mtpt on m_product_type.id = mtpt.product_type_id) as m " +
            "join m_textile mt on mt.id = m.textile_id where mt.name = :name", nativeQuery = true)
    List<Textile> findAllByProductTypes(String name);


    boolean existsByName(String name);
}
