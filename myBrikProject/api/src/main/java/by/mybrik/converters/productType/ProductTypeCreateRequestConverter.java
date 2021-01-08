package by.mybrik.converters.productType;

import by.mybrik.controllers.requests.productTypeRequests.ProductTypeCreate;
import by.mybrik.domain.ProductType;
import org.springframework.stereotype.Component;

@Component
public class ProductTypeCreateRequestConverter
    extends ProductTypeConverter<ProductTypeCreate, ProductType> {
  @Override
  public ProductType convert(ProductTypeCreate request) {

    ProductType type = new ProductType();

    return doConvert(type, request);
  }
}
