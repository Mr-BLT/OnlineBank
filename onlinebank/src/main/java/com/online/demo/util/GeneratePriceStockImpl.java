package com.online.demo.util;

import org.springframework.stereotype.Component;

@Component
public class GeneratePriceStockImpl implements GeneratePriceStock {

  @Override
  public int price(int nowPrice) {
    return random(nowPrice + 25, Math.max(1, nowPrice - 25));
  }


  public int random(int max, int min) {
    return (int) (Math.random() * (max - min)) + min;
  }

}
