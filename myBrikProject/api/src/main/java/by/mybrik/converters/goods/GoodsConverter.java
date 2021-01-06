package by.mybrik.converters.goods;

import by.mybrik.controllers.requests.goodsRequests.GoodsCreate;
import by.mybrik.domain.Goods;
import org.springframework.core.convert.converter.Converter;

public abstract class GoodsConverter<S, T> implements Converter<S, T> {

  protected Goods doConvert(Goods product, GoodsCreate request) {

    product.setOrderCode(request.getOrderCode());
    product.setName(request.getName());
    product.setPhoto(request.getPhoto());
    product.setGender(request.getGender());
    product.setSize(request.getSize());
    product.setColor(request.getColor());
    product.setDescription(request.getDescription());
    product.setDeleted(request.isDeleted());
    product.setPrice(request.getPrice());
    product.setQuantity(request.getQuantity());
    product.setCategory(request.getCategory());

    return product;
  }
}
