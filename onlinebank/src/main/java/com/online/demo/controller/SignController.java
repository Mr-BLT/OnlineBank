package com.online.demo.controller;

import com.online.demo.dto.UserForm;
import com.online.demo.service.SignUp;
import javax.annotation.security.PermitAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SignController {

  @Autowired
  private SignUp signUpService;

  @PermitAll
  @GetMapping("/In")
  public String fetIn() {
    return "sign_in_page";
  }

  @PermitAll
  @GetMapping("/Up")
  public String getUp() {
    return "sign_up_page";
  }


  @PermitAll
  @PostMapping("/Up")
  public String signUp(UserForm userForm) {
    signUpService.signUp(userForm);
    return "redirect:/In";
  }
}
