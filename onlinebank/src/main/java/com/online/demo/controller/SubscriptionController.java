package com.online.demo.controller;

import com.online.demo.model.Subscription;
import com.online.demo.model.User;
import com.online.demo.model.form.SubscriptionForm;
import com.online.demo.service.SubscriptionService;
import com.online.demo.service.UserService;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.security.PermitAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SubscriptionController {

  @Autowired
  private SubscriptionService subscriptionService;

  @Autowired
  private UserService userService;

  @PreAuthorize("hasAnyAuthority('USER')")
  @PostMapping("/s/sub")
  public ResponseEntity sub(@RequestParam("id") Long id,
      Authentication authentication) {

    User user = userService.getUser(Long.valueOf(authentication.getName()));

    int result = subscriptionService.subscribe(id, user.getId());
    if (result == 1) {
      return ResponseEntity.ok(user.getAccountBankList().getSuma());
    } else if (result == 0) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.badRequest().build();
  }

  @PreAuthorize("hasAnyAuthority('USER')")
  @PostMapping("/s/unsub")
  public ResponseEntity unsub(@RequestParam("id") Long id, Authentication authentication) {

    User user = userService.getUser(Long.valueOf(authentication.getName()));

    int result = subscriptionService.unsubscribe(id, user.getId());

    if (result == 1) {
      return ResponseEntity.ok(HttpStatus.OK);
    }
    return ResponseEntity.notFound().build();
  }

  //@PermitAll
  @PreAuthorize("hasAnyAuthority('USER')")
  @GetMapping("/subscription")
  public String get(Model model, Authentication authentication) {

    User user = userService.getUser(Long.valueOf(authentication.getName()));

    List<Subscription> subscriptions = subscriptionService.getAllSubscb();
    List<Subscription> usersubscriptions = subscriptionService.getUserSubscb(user.getId());

    List<SubscriptionForm> subscriptionForms = new ArrayList<>();

    for (int i = 0; i < subscriptions.size(); i++) {
      Subscription subscription = subscriptions.get(i);
      subscriptionForms.add(
          SubscriptionForm.builder()
              .name(subscription.getName())
              .price(subscription.getPrice())
              .id(subscription.getId())
              .flag(true)
              .build()
      );
    }

    for (int i = 0; i < usersubscriptions.size(); i++) {
      int id = usersubscriptions.get(i).getId().intValue() - 1;
      subscriptionForms.get(id).setFlag(false);
    }

    model.addAttribute("accountbank", user.getAccountBankList());
    model.addAttribute("subscriptions", subscriptionForms);

    return "subscriptions_page";
  }

}
