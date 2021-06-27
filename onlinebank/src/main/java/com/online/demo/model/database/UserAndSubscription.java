package com.online.demo.model.database;

import com.online.demo.model.AccountBank;
import com.online.demo.model.Subscription;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.bytebuddy.implementation.bind.annotation.Empty;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "SubscriptionAndAccountBank")
public class UserAndSubscription {

  @Id
  @GeneratedValue
  private Long id;

  @OneToOne
  private AccountBank accountBank;

  @OneToOne
  private Subscription subscription;

  private String start;
}
