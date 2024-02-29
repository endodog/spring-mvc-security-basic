package com.endodog.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

  @GetMapping("/")
  public String index() {
    return "index";
  }

  @GetMapping("/user")
  public String user() {
    return "user/user";
  }

  @GetMapping("/employee/view")
  public String employee() {
    return "employee/emp";
  }

  @GetMapping("/admin/view")
  public String admin() {
    return "admin/admin";
  }

}
