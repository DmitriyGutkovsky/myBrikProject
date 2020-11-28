package by.mybrik.service;

import by.mybrik.domain.ProductType;

import java.util.List;
import java.util.Optional;

public interface ProductTypeService {

    ProductType save(ProductType type);

    List<ProductType> findAll();

    ProductType findById(Long id);

    Optional<ProductType> findOne(Long id);

    ProductType update(ProductType type);

    Long delete(ProductType type);

}
