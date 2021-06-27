package com.online.demo.model.form;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SubscriptionForm {

  private Long id;
  private String name;
  private Long price;
  private boolean flag;
}
