package com.PedroA10.Estufa.service;


import com.PedroA10.Estufa.exception.UserNotFoundException;
import com.PedroA10.Estufa.model.User;
import com.PedroA10.Estufa.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

  @Autowired
  UserRepository userRepository;

    public List<User> findAll() {
      return userRepository.findAll();
    }
    
    public Optional<User> findById(Long id) {
      return userRepository.findById(id);
    }

    public void deleteById(Long id) {
      userRepository.deleteById(id);
    }

    public User createUser(@Valid User user) {
      return userRepository.save(user);
    }

  public User findEntityById(Long id) {
    return userRepository.findById(id)
      .orElseThrow(() -> new UserNotFoundException("User not found"));
  }
}
