package by.mybrik.converters.productType;

import by.mybrik.controllers.requests.productTypeRequests.ProductTypeUpdate;
import by.mybrik.domain.ProductType;
import by.mybrik.exceptions.EntityNotFoundException;
import by.mybrik.repository.impl.ProductTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
@RequiredArgsConstructor
public class ProductTypeUpdateRequestConverter
    extends ProductTypeConverter<ProductTypeUpdate, ProductType> {

  public final ProductTypeRepository productTypeRepository;

  @Override
  public ProductType convert(ProductTypeUpdate request) {

    ProductType type =
        productTypeRepository
            .findById(request.getId())
            .orElseThrow(
                () ->
                    new EntityNotFoundException(
                        String.format("There is no product with id = %d", request.getId())));
    type.setChanged(new Timestamp(System.currentTimeMillis()));

    return doConvert(type, request);
  }
}
