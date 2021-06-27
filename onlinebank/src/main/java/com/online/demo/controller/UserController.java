package com.online.demo.controller;

import com.online.demo.model.AccountBank;
import com.online.demo.model.User;
import com.online.demo.repository.AccountBankRepository;
import com.online.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

  @Autowired
  private UserService userService;

  @Autowired
  private AccountBankRepository accountBankRepository;

  //@PreAuthorize("hasAnyAuthority('USER')")
  @PreAuthorize("isAuthenticated()")
  @GetMapping("/profile")
  public String getProfile(Authentication authentication, Model model) {

    User user = userService.getUser(Long.valueOf(authentication.getName()));
    AccountBank accountBank = userService.getAccountBank(user.getAccountBankList().getId());

    model.addAttribute("user", user);
    model.addAttribute("accountbank", accountBank);
    return "profile_page";
  }

}
