package by.mybrik.converters.orders;

import by.mybrik.controllers.requests.individualOrderRequests.IndividualOrderCreate;
import by.mybrik.domain.IndividualOrder;
import org.springframework.core.convert.converter.Converter;

public abstract class IndividualOrderConverter<S, T> implements Converter<S, T> {

  protected IndividualOrder doConvert(IndividualOrder order, IndividualOrderCreate request) {

    order.setUserId(request.getUserId());
    order.setTextileId(request.getTextileId());
    order.setProductTypeId(request.getProductTypeId());
    order.setPriceId(request.getPriceId());
    order.setQuantity(request.getQuantity());

    return order;
  }
}
