package by.mybrik.converters.prices;

import by.mybrik.controllers.requests.priceForIndividualRequests.PriceForIndividualUpdate;
import by.mybrik.domain.PriceForIndividualOrder;
import by.mybrik.exceptions.EntityNotFoundException;
import by.mybrik.repository.impl.PriceForIndividualOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
@RequiredArgsConstructor
public class PriceForIndividualOrderUpdateRequestConverter
    extends PriceForIndividualOrderConverter<PriceForIndividualUpdate, PriceForIndividualOrder> {

  public final PriceForIndividualOrderRepository priceForIndividualOrderRepository;

  @Override
  public PriceForIndividualOrder convert(PriceForIndividualUpdate request) {

    PriceForIndividualOrder price =
        priceForIndividualOrderRepository
            .findById(request.getId())
            .orElseThrow(
                () ->
                    new EntityNotFoundException(
                        String.format(
                            "There is no such price for individual order with id = %d",
                            request.getId())));
    price.setChanged(new Timestamp(System.currentTimeMillis()));

    return doConvert(price, request);
  }
}
