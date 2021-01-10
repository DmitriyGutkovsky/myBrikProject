package by.mybrik.controllers.requests.individualOrderRequests;

import by.mybrik.domain.OrderStatus;
import lombok.Data;

@Data
public class IndividualOrderUpdate extends IndividualOrderCreate {

  private Long id;

  private OrderStatus orderStatus;
}
