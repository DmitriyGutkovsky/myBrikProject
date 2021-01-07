package by.mybrik.converters.textile;

import by.mybrik.controllers.requests.textileRequests.TextileUpdate;
import by.mybrik.domain.Textile;
import by.mybrik.exceptions.EntityNotFoundException;
import by.mybrik.repository.impl.TextileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
@RequiredArgsConstructor
public class TextileUpdateRequestConverter extends TextileConverter<TextileUpdate, Textile> {

  public final TextileRepository textileRepository;

  @Override
  public Textile convert(TextileUpdate request) {
    Textile textile =
        textileRepository
            .findById(request.getId())
            .orElseThrow(
                () ->
                    new EntityNotFoundException(
                        String.format("There is no textile with id = %d", request.getId())));
    textile.setChanged(new Timestamp(System.currentTimeMillis()));
    return doConvert(textile, request);
  }
}
