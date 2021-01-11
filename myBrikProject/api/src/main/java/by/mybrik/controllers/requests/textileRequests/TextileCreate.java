package by.mybrik.controllers.requests.textileRequests;

import lombok.Data;

@Data
public class TextileCreate {

  private String code;

  private String name;

  private String color;

  private String description;

  private String photo;

  private boolean isDeleted;
}
