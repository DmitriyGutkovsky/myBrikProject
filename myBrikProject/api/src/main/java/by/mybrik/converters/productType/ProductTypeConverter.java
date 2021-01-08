package by.mybrik.converters.productType;

import by.mybrik.controllers.requests.productTypeRequests.ProductTypeCreate;
import by.mybrik.domain.ProductType;
import org.springframework.core.convert.converter.Converter;

public abstract class ProductTypeConverter<S, T> implements Converter<S, T> {

  protected ProductType doConvert(ProductType type, ProductTypeCreate request) {

    type.setProductType(request.getProductType());
    type.setPhoto(request.getPhoto());
    type.setDeleted(request.isDeleted());

    return type;
  }
}
