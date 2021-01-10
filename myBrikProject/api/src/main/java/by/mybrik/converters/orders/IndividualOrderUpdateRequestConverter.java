package by.mybrik.converters.orders;

import by.mybrik.controllers.requests.individualOrderRequests.IndividualOrderUpdate;
import by.mybrik.domain.IndividualOrder;
import by.mybrik.domain.PriceForIndividualOrder;
import by.mybrik.exceptions.EntityNotFoundException;
import by.mybrik.repository.impl.IndividualOrderRepository;
import by.mybrik.repository.impl.PriceForIndividualOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
@RequiredArgsConstructor
public class IndividualOrderUpdateRequestConverter
    extends IndividualOrderConverter<IndividualOrderUpdate, IndividualOrder> {

  public final IndividualOrderRepository individualOrderRepository;

  public final PriceForIndividualOrderRepository priceForIndividualOrderRepository;

  @Override
  public IndividualOrder convert(IndividualOrderUpdate request) {

    IndividualOrder updateOrder =
        individualOrderRepository
            .findById(request.getId())
            .orElseThrow(
                () ->
                    new EntityNotFoundException(
                        String.format(
                            "There is no individual order with id = %d", request.getId())));

    Long priceId = request.getPriceId();
    PriceForIndividualOrder individualOrderPrice =
        priceForIndividualOrderRepository
            .findById(priceId)
            .orElseThrow(
                () -> new EntityNotFoundException("There is no such price for individual order"));

    Double pricePerOnePcs = individualOrderPrice.getPrice();
    Double totalPrice = pricePerOnePcs * request.getQuantity();

    updateOrder.setTotalPrice(totalPrice);
    updateOrder.setOrderStatus(request.getOrderStatus());
    updateOrder.setChanged(new Timestamp(System.currentTimeMillis()));

    return doConvert(updateOrder, request);
  }
}
