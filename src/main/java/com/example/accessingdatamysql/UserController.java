package com.example.accessingdatamysql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path="/demo")
public class UserController {
  @Autowired
  private UserRepository userRepository;

  @PostMapping(path="/add")
  public @ResponseBody String addNewUser (@RequestParam String name, @RequestParam String email) {
    UserModel n = new UserModel();
    n.setName(name);
    n.setEmail(email);
    userRepository.save(n);
    return "Saved";
}

  @GetMapping(path="/all")
  public @ResponseBody Iterable<UserModel> getAllUsers() {
    return userRepository.findAll();
  }
}
