package by.mybrik.converters.orders;

import by.mybrik.controllers.requests.individualOrderRequests.IndividualOrderCreate;
import by.mybrik.domain.IndividualOrder;
import by.mybrik.domain.OrderStatus;
import by.mybrik.domain.PriceForIndividualOrder;
import by.mybrik.exceptions.EntityNotFoundException;
import by.mybrik.repository.impl.IndividualOrderRepository;
import by.mybrik.repository.impl.PriceForIndividualOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class IndividualOrderCreateRequestConverter
    extends IndividualOrderConverter<IndividualOrderCreate, IndividualOrder> {

  public final IndividualOrderRepository individualOrderRepository;

  public final PriceForIndividualOrderRepository priceForIndividualOrderRepository;

  @Override
  public IndividualOrder convert(IndividualOrderCreate request) {

    Long priceId = request.getPriceId();

    PriceForIndividualOrder individualOrderPrice =
        priceForIndividualOrderRepository
            .findById(priceId)
            .orElseThrow(
                () -> new EntityNotFoundException("There is no such price for individual order"));
    Double pricePerOnePcs = individualOrderPrice.getPrice();
    Double totalPrice = pricePerOnePcs * request.getQuantity();

    IndividualOrder order = new IndividualOrder();

    order.setTotalPrice(totalPrice);
    order.setOrderStatus(OrderStatus.SEND);

    return doConvert(order, request);
  }
}
