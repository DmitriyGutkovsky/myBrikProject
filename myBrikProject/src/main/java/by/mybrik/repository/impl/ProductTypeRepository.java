package by.mybrik.repository.impl;

import by.mybrik.domain.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductTypeRepository extends JpaRepository<ProductType, Long> {

  ProductType findByProductType(String type);

  List<ProductType> findAllByIsDeletedIsFalse();
}
