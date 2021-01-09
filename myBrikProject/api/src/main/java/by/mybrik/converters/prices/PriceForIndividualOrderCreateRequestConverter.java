package by.mybrik.converters.prices;

import by.mybrik.controllers.requests.priceForIndividualRequests.PriceForIndividualCreate;
import by.mybrik.domain.PriceForIndividualOrder;
import org.springframework.stereotype.Component;

@Component
public class PriceForIndividualOrderCreateRequestConverter
    extends PriceForIndividualOrderConverter<PriceForIndividualCreate, PriceForIndividualOrder> {
  @Override
  public PriceForIndividualOrder convert(PriceForIndividualCreate request) {

    PriceForIndividualOrder price = new PriceForIndividualOrder();

    return doConvert(price, request);
  }
}
