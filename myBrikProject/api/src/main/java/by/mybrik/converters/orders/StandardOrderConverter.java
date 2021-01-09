package by.mybrik.converters.orders;

import by.mybrik.controllers.requests.standardOrderRequests.StandardOrderCreate;
import by.mybrik.domain.StandardOrder;
import org.springframework.core.convert.converter.Converter;

public abstract class StandardOrderConverter<S, T> implements Converter<S, T> {

  protected StandardOrder doConvert(StandardOrder order, StandardOrderCreate request) {

    order.setGoodId(request.getGoodId());
    order.setUserId(request.getUserId());
    order.setQuantity(request.getQuantity());

    return order;
  }
}
