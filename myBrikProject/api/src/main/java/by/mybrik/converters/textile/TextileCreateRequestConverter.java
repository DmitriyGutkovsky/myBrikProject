package by.mybrik.converters.textile;

import by.mybrik.controllers.requests.textileRequests.TextileCreate;
import by.mybrik.domain.Textile;
import org.springframework.stereotype.Component;

@Component
public class TextileCreateRequestConverter extends TextileConverter<TextileCreate, Textile> {
  @Override
  public Textile convert(TextileCreate request) {

    Textile textile = new Textile();

    return doConvert(textile, request);
  }
}
