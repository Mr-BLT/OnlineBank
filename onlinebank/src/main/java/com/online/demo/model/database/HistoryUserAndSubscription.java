package com.online.demo.model.database;

import com.online.demo.model.AccountBank;
import com.online.demo.model.Subscription;
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
@Entity
@Table(name = "HistorySubscriptionAndAccoundBank")
@Builder
public class HistoryUserAndSubscription {

  @Id
  @GeneratedValue
  private Long id;

  @OneToOne
  private AccountBank accountBank;

  @OneToOne
  private Subscription subscription;
  private String start;
  private String finish;
}
