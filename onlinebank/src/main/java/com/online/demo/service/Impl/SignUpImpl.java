package com.online.demo.service.Impl;

import com.online.demo.dto.UserForm;
import com.online.demo.model.AccountBank;
import com.online.demo.model.User;
import com.online.demo.model.User.Role;
import com.online.demo.model.User.State;
import com.online.demo.repository.AccountBankRepository;
import com.online.demo.repository.UserRepository;
import com.online.demo.service.SignUp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class SignUpImpl implements SignUp {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private AccountBankRepository accountBankRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Override
  public void signUp(UserForm userForm) {
    User newUser = User.builder()
        .name(userForm.getName())
        .lastname(userForm.getLastname())
        .password(passwordEncoder.encode(userForm.getPassword()))
        .number(userForm.getNumber())
        .state(State.ACTIVE)
        .role(Role.USER)
        //.numberCard(String.valueOf(UUID.randomUUID()).substring(0, 12))
        .build();

    AccountBank accountBank = AccountBank.builder()
        .numberCard(String.valueOf(UUID.randomUUID()).substring(0, 12))
        .suma(1000L)
        .build();

    userRepository.save(newUser);

    accountBank.setUser(newUser);
    accountBankRepository.save(accountBank);

    newUser.setAccountBankList(accountBank);
    userRepository.save(newUser);
  }
}
