package com.online.demo.controller;

import com.online.demo.model.User;
import com.online.demo.service.AdminService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AdminController {

  @Autowired
  public AdminService adminService;

  @PreAuthorize("hasAnyAuthority('ADMIN')")
  @GetMapping("/admin")
  public String getAuth(Model model) {
    List<User> users = adminService.findAllUsers();
    model.addAttribute("users", users);
    return "admin_profile_page";
  }

  @PreAuthorize("hasAnyAuthority('ADMIN')")
  @PostMapping("/admin/ban")
  public ResponseEntity ban(@RequestParam("id") Long id) {
    adminService.ban(id);
    return ResponseEntity.ok("ban");
  }

  @PreAuthorize("hasAnyAuthority('ADMIN')")
  @PostMapping("/admin/unban")
  public ResponseEntity unban(@RequestParam("id") Long id) {
    adminService.unban(id);
    return ResponseEntity.ok("unban");
  }
}
