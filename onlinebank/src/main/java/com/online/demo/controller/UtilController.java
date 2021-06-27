package com.online.demo.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UtilController {

  @PreAuthorize("isAuthenticated()")
  @GetMapping("/help")
  public String getHelp(Authentication authentication) {
    return "sign_in_page";
  }
}
