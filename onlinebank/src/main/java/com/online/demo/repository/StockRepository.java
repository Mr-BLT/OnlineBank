package com.online.demo.repository;

import com.online.demo.model.Stock;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository extends JpaRepository<Stock, Long> {

  public Stock getById(Long stockId);
  public List<Stock> getByNameLike(String name);
}
