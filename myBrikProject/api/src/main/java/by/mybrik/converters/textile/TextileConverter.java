package by.mybrik.converters.textile;

import by.mybrik.controllers.requests.textileRequests.TextileCreate;
import by.mybrik.domain.Textile;
import org.springframework.core.convert.converter.Converter;

public abstract class TextileConverter<S, T> implements Converter<S, T> {

  protected Textile doConvert(Textile textile, TextileCreate request) {

    textile.setCode(request.getCode());
    textile.setName(request.getName());
    textile.setColor(request.getColor());
    textile.setDescription(request.getDescription());
    textile.setPhoto(request.getPhoto());
    textile.setDeleted(request.isDeleted());

    return textile;
  }
}
