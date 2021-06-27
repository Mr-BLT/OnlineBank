package com.online.demo.service;

import com.online.demo.model.Stock;
import com.online.demo.model.form.StockForm;
import java.util.List;

public interface StockService {

  public int buyStock(Long userId, Long stockId, Long count);

  public int sellStock(Long userId, Long stockId, Long count);

  public List<Stock> getStockListLike(Long stockId);

  public List<Stock> getStockList();

  public List<StockForm> getUserStockList(Long userId);
}
