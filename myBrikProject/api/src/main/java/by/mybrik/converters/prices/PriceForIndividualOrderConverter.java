package by.mybrik.converters.prices;

import by.mybrik.controllers.requests.priceForIndividualRequests.PriceForIndividualCreate;
import by.mybrik.domain.PriceForIndividualOrder;
import org.springframework.core.convert.converter.Converter;

public abstract class PriceForIndividualOrderConverter<S, T> implements Converter<S, T> {
  protected PriceForIndividualOrder doConvert(
      PriceForIndividualOrder price, PriceForIndividualCreate request) {

    price.setProductType(request.getProductType());
    price.setPrice(request.getPrice());
    price.setDeleted(request.isDeleted());

    return price;
  }
}
