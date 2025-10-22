package com.PedroA10.Estufa.controller;

import com.PedroA10.Estufa.dto.userdto.UserRequestDTO;
import com.PedroA10.Estufa.dto.userdto.UserResponseDTO;
import com.PedroA10.Estufa.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

  @Autowired
  UserService userService;

  @GetMapping
  public List<UserResponseDTO> listUser() {
    return userService.findAll();
  }

  @GetMapping("/{id}")
  public ResponseEntity<UserResponseDTO> listUserById(@PathVariable Long id){
    return userService.findById(id)
      .map(ResponseEntity:: ok)
      .orElse(ResponseEntity.notFound().build());
  }

  @PostMapping
  public ResponseEntity<UserResponseDTO> createUser(@RequestBody @Valid UserRequestDTO userRequestDTO) {
    try {
      UserResponseDTO newUser = userService.createUser(userRequestDTO);
      return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }catch (IllegalArgumentException e) {
      return ResponseEntity.badRequest().build();
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
    try {
      userService.deleteById(id);
      return ResponseEntity.noContent().build();
    }catch (IllegalArgumentException e) {
      return ResponseEntity.notFound().build();
    }
  }
}
