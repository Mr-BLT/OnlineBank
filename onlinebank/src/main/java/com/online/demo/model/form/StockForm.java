package com.online.demo.model.form;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StockForm {

  private Long id;
  private String name;
  private Integer price;
  private Long count;
  private Long sockId;


  public StockForm(Long id, String name, Integer price,
      Long count) {
    this.name = name;
    this.price = price;
    this.count = count;
    this.id = id;
  }
}
