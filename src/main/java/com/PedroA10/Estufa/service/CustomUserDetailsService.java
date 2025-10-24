package com.PedroA10.Estufa.service;

import com.PedroA10.Estufa.model.User;
import com.PedroA10.Estufa.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

  private final UserRepository userRepository;

  @Autowired
  public CustomUserDetailsService(UserRepository userRepository){
    this.userRepository = userRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = userRepository.findByUsername(username)
      .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
    return org.springframework.security.core.userdetails.User
      .withUsername(user.getUsername())
      .password(user.getPassword())
      .authorities("USER") // roles fixas por enquanto
      .build();
  }
}
