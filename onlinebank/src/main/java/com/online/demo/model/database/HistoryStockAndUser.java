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
@Table(name = "HisotyrStockAndAccountBank")
public class HistoryStockAndUser {

  @Id
  @GeneratedValue
  private Long id;

  @OneToOne
  private AccountBank accountBank;

  @OneToOne
  private Stock stock;

  private String time;
  private Long count;
  private Long status;
}
