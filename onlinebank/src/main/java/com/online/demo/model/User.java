package com.online.demo.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "account")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  @Column(nullable = false)
  private String name;
  @Column(nullable = false)
  private String lastname;
  @Column(nullable = false)
  private String number;
  @Column(nullable = false)
  private String password;

  @OneToOne
  private AccountBank accountBankList;

  @Enumerated(value = EnumType.STRING)
  private Role role;
  @Enumerated(value = EnumType.STRING)
  private State state;


  public enum Role {USER, ADMIN}

  public enum State {ACTIVE, BANNED;}

  public boolean isActive() {
    return this.state == State.ACTIVE;
  }

  public boolean isBanned() {
    return this.state == State.BANNED;
  }

  public boolean isAdmin() {
    return this.role == Role.ADMIN;
  }
}
