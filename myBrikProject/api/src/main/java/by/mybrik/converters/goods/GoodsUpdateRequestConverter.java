package by.mybrik.converters.goods;

import by.mybrik.controllers.requests.goodsRequests.GoodsUpdate;
import by.mybrik.domain.Goods;
import by.mybrik.exceptions.EntityNotFoundException;
import by.mybrik.repository.impl.GoodsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
@RequiredArgsConstructor
public class GoodsUpdateRequestConverter extends GoodsConverter<GoodsUpdate, Goods> {

  public final GoodsRepository goodsRepository;

  @Override
  public Goods convert(GoodsUpdate request) {
    Goods product =
        goodsRepository
            .findById(request.getId())
            .orElseThrow(
                () ->
                    new EntityNotFoundException(
                        String.format("There is no product with id = %d", request.getId())));
    product.setChanged(new Timestamp(System.currentTimeMillis()));

    return doConvert(product, request);
  }
}
