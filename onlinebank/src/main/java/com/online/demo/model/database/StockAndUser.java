package com.online.demo.model.database;

import com.online.demo.model.AccountBank;
import com.online.demo.model.Stock;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "StockAndAccountBank")
public class StockAndUser {

  @Id
  @GeneratedValue
  private Long id;

  @OneToOne
  private AccountBank accountBank;
  @OneToOne
  private Stock stock;
  private Long count;

}
