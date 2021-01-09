package by.mybrik.converters.orders;

import by.mybrik.controllers.requests.standardOrderRequests.StandardOrderUpdate;
import by.mybrik.domain.Goods;
import by.mybrik.domain.StandardOrder;
import by.mybrik.exceptions.EntityNotFoundException;
import by.mybrik.repository.impl.GoodsRepository;
import by.mybrik.repository.impl.StandardOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class StandardOrderUpdateRequestConverter
    extends StandardOrderConverter<StandardOrderUpdate, StandardOrder> {

  public final StandardOrderRepository standardOrderRepository;

  public final GoodsRepository goodsRepository;

  @Override
  public StandardOrder convert(StandardOrderUpdate request) {

    if (!goodsRepository.existsById(request.getGoodId())) {
      throw new EntityNotFoundException("There is no such product");
    }

    Long productId = request.getGoodId();
    Optional<Goods> productForPurchase = goodsRepository.findById(productId);
    Double pricePerOnePcs = productForPurchase.get().getPrice();
    Double totalPrice = pricePerOnePcs * request.getQuantity();

    StandardOrder updateOrder =
        standardOrderRepository
            .findById(request.getId())
            .orElseThrow(
                () ->
                    new EntityNotFoundException(
                        String.format("There is no standard order with id = %d", request.getId())));

    updateOrder.setTotalPrice(totalPrice);
    updateOrder.setOrderStatus(request.getOrderStatus());
    updateOrder.setChanged(new Timestamp(System.currentTimeMillis()));

    return doConvert(updateOrder, request);
  }
}
