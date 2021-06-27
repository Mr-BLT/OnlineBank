package com.online.demo.model.database;

import com.online.demo.model.AccountBank;
import com.online.demo.model.User;
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
@Table(name = "HistoryAccountBankTransaction")
public class HistoryUserTransaction {

  @Id
  @GeneratedValue
  private Long id;
  @OneToOne
  private AccountBank accountBankForm;
  @OneToOne
  private AccountBank accountBankTo;
  private Long suma;
  private String date;
}
