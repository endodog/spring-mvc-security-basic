package com.endodog.demo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

  @Value("${spring.security.user.name}")
  private String userN;

  @Value("${spring.security.user.password}")
  private String passW;

  @GetMapping("/login")
  public String login() {
    return "login";
  }

  @PostMapping("/processing")
  public String loginProcessing(@RequestParam("username") String username, @RequestParam("password") String password) {
    if (username.equals(userN) && password.equals(passW)) {
      return "redirect:/";
    }
    return "login";
  }


}
