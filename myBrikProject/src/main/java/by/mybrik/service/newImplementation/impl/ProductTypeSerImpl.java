package by.mybrik.service.newImplementation.impl;

import by.mybrik.domain.entities.ProductType;
import by.mybrik.repository.newImplementation.ProductTypeRep;
import by.mybrik.service.newImplementation.ProductTypeSer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductTypeSerImpl implements ProductTypeSer {

    private final ProductTypeRep productTypeRepository;


    @Override
    public ProductType save(ProductType type) {
        return productTypeRepository.save(type);
    }

    @Override
    public List<ProductType> findAll() {
        return productTypeRepository.findAll();
    }

    @Override
    public ProductType findById(Long id) {
        return productTypeRepository.findById(id);
    }

    @Override
    public Optional<ProductType> findOne(Long id) {
        return productTypeRepository.findOne(id);
    }

    @Override
    public ProductType update(ProductType type) {
        return productTypeRepository.update(type);
    }

    @Override
    public Long delete(ProductType type) {
        return productTypeRepository.delete(type);
    }
}
