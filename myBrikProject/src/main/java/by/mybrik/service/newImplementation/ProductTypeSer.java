package by.mybrik.service.newImplementation;


import by.mybrik.domain.entities.ProductType;

import java.util.List;
import java.util.Optional;

public interface ProductTypeSer {

    ProductType save(ProductType type);

    List<ProductType> findAll();

    ProductType findById(Long id);

    Optional<ProductType> findOne(Long id);

    ProductType update(ProductType type);

    Long delete(ProductType type);

}
