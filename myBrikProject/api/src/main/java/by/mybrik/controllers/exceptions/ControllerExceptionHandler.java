package by.mybrik.controllers.exceptions;

import by.mybrik.controllers.responces.ErrorMessage;
import by.mybrik.exceptions.EntityNotFoundException;
import lombok.extern.log4j.Log4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Log4j
public class ControllerExceptionHandler {

  @ExceptionHandler(EntityNotFoundException.class)
  public ResponseEntity<ErrorMessage> handleOthersException(Exception e) {
    /* Handles all other exceptions. Status code 400. */
    log.error(e.getMessage(), e);
    log.info(e.getMessage(), e);
    return new ResponseEntity<>(new ErrorMessage(e.getMessage()), HttpStatus.BAD_REQUEST);
  }
}
