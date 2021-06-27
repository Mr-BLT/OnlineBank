package com.online.demo.service.Impl;

import com.online.demo.model.AccountBank;
import com.online.demo.model.User;
import com.online.demo.model.database.UserFriendNumbers;
import com.online.demo.repository.AccountBankRepository;
import com.online.demo.repository.UserFriendNumbersRepository;
import com.online.demo.repository.UserRepository;
import com.online.demo.service.UserService;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private UserFriendNumbersRepository userFriendNumbersRepository;

  @Autowired
  private AccountBankRepository accountBankRepository;

  @Override
  public User getUser(Long id) {
    User user = userRepository.findById(id)
        .orElseThrow(() -> new UsernameNotFoundException("User not found!!!"));
    return user;
  }

  @Override
  public AccountBank getAccountBank(Long id) {
    AccountBank accountBank = accountBankRepository.findById(id).orElse(new AccountBank());
    return accountBank;
  }

  @Override
  public User getUserByNumber(String number) {
    return userRepository.getByNumber(number);
  }

  @Override
  public List<UserFriendNumbers> getListNumbers(Long userId, Long limit) {
    List<UserFriendNumbers> set =
        userFriendNumbersRepository.getUserFriendNumbersBy(userId, limit);

    return new ArrayList<>(set);
  }

  @Override
  public void addUserFriendNumbers(Long userId, String number) {
    userFriendNumbersRepository
        .save(
            UserFriendNumbers.builder().user(userRepository.getById(userId)).name(number).build());
  }
}
