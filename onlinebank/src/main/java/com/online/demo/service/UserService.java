package com.online.demo.service;

import com.online.demo.model.AccountBank;
import com.online.demo.model.User;
import com.online.demo.model.database.UserFriendNumbers;
import java.util.List;

public interface UserService {

  User getUser(Long id);

  AccountBank getAccountBank(Long id);

  User getUserByNumber(String number);

  public List<UserFriendNumbers> getListNumbers(Long userId, Long limit);

  public void addUserFriendNumbers(Long userId, String number);
}
