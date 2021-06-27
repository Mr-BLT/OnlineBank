package com.online.demo.controller;

import com.online.demo.model.User;
import com.online.demo.service.TransactionService;
import com.online.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class TransactionController {

  @Autowired
  private UserService userService;

  @Autowired
  private TransactionService transactionService;

  @PreAuthorize("hasAnyAuthority('USER')")
  @GetMapping("/transaction")
  public String getTranslation(Authentication authentication, Model model) {

    User user = userService.getUser(Long.valueOf(authentication.getName()));
    Long suma = user.getAccountBankList().getSuma();

    model.addAttribute("suma", suma);
    model.addAttribute("historyNumbers",
        userService.getListNumbers(user.getId(), 5L));

    return "transaction_page";
  }


  @PreAuthorize("hasAnyAuthority('USER')")
  @PostMapping("/transaction")
  public ResponseEntity<Long> getU(Authentication authentication,
      @RequestParam("number") String number,
      @RequestParam("suma") Long suma) {

    User user = userService.getUser(Long.valueOf(authentication.getName()));

    int result = transactionService.transactiobyNumber(number, user, suma);

    if (result == 1) {
      userService.addUserFriendNumbers(user.getId(), number);
      return ResponseEntity.ok(user.getAccountBankList().getSuma());
    } else if (result == 0) {
      return ResponseEntity.badRequest().build();
    }
    return ResponseEntity.notFound().build();
  }

}
