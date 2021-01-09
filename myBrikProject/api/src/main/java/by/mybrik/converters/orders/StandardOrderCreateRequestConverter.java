package by.mybrik.converters.orders;

import by.mybrik.controllers.requests.standardOrderRequests.StandardOrderCreate;
import by.mybrik.domain.Goods;
import by.mybrik.domain.OrderStatus;
import by.mybrik.domain.StandardOrder;
import by.mybrik.exceptions.EntityNotFoundException;
import by.mybrik.repository.impl.GoodsRepository;
import by.mybrik.repository.impl.StandardOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class StandardOrderCreateRequestConverter
    extends StandardOrderConverter<StandardOrderCreate, StandardOrder> {

  public final StandardOrderRepository orderRepository;

  public final GoodsRepository goodsRepository;

  @Override
  public StandardOrder convert(StandardOrderCreate request) {

    if (!goodsRepository.existsById(request.getGoodId())) {
      throw new EntityNotFoundException("There is no such product, please check again");
    }

    Long productId = request.getGoodId();
    Optional<Goods> productForPurchase = goodsRepository.findById(productId);
    Double pricePerOnePcs = productForPurchase.get().getPrice();
    Double totalPrice = pricePerOnePcs * request.getQuantity();

    StandardOrder order = new StandardOrder();

    order.setTotalPrice(totalPrice);
    order.setOrderStatus(OrderStatus.SEND);

    return doConvert(order, request);
  }
}
