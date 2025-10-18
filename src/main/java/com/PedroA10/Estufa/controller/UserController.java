package com.PedroA10.Estufa.controller;

import com.PedroA10.Estufa.model.User;
import com.PedroA10.Estufa.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

  @Autowired
  UserService userService;

  @GetMapping
  public List<User> listUser() {
    return userService.findAll();
  }

  @GetMapping("{/id}")
  public Optional<User> listUserById(@PathVariable Long id){
    return userService.findById(id);
  }

  @PostMapping
  public ResponseEntity<User> createUser(@RequestBody @Valid User user) {
    try {
      User newUser = userService.createUser(user);
      return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }catch (IllegalArgumentException e) {
      return ResponseEntity.badRequest().build();
    }
  }

  @DeleteMapping("{/id}")
  public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
    try {
      userService.deleteById(id);
      return ResponseEntity.noContent().build();
    }catch (IllegalArgumentException e) {
      return ResponseEntity.notFound().build();
    }
  }
}
