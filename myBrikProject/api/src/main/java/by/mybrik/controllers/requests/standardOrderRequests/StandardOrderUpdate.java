package by.mybrik.controllers.requests.standardOrderRequests;

import by.mybrik.domain.OrderStatus;
import lombok.Data;

@Data
public class StandardOrderUpdate extends StandardOrderCreate {

  private Long id;

  private OrderStatus orderStatus;
}
