package com.online.demo.background;

import com.online.demo.model.Stock;
import com.online.demo.repository.StockRepository;
import com.online.demo.util.GeneratePriceStock;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class StockThread {

  @Autowired
  private StockRepository stockRepository;

  @Autowired
  private GeneratePriceStock generatePriceStock;

  //@Scheduled(fixedDelay  = 3999)
  public void doSomething() {

    List<Stock> stockList = stockRepository.findAll();

    for (Stock stock : stockList) {
      stock.setPrice(generatePriceStock.price(stock.getPrice()));
      stockRepository.save(stock);
    }
  }

}
