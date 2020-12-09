package by.mybrik.service.impl;

import by.mybrik.domain.entities.ProductType;
import by.mybrik.repository.ProductTypeRepository;
import by.mybrik.service.ProductTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductTypeServiceImpl implements ProductTypeService {

    private final ProductTypeRepository productTypeRepository;


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
