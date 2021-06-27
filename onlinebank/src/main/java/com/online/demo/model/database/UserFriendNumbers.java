package com.online.demo.model.database;

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
@Table
public class UserFriendNumbers {

  @Id
  @GeneratedValue
  private Long id;

  @OneToOne
  private User user;

  private String name;

}
