package by.mybrik.converters.goods;

import by.mybrik.controllers.requests.goodsRequests.GoodsCreate;
import by.mybrik.domain.Goods;
import org.springframework.stereotype.Component;

@Component
public class GoodsCreateRequestConverter extends GoodsConverter<GoodsCreate, Goods> {
  @Override
  public Goods convert(GoodsCreate request) {

    Goods product = new Goods();

    return doConvert(product, request);
  }
}
