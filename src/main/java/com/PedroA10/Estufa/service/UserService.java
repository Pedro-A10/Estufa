package com.PedroA10.Estufa.service;


import com.PedroA10.Estufa.dto.userdto.UserRequestDTO;
import com.PedroA10.Estufa.dto.userdto.UserResponseDTO;
import com.PedroA10.Estufa.exception.EmailNotFoundException;
import com.PedroA10.Estufa.exception.UserAlreadyExistsException;
import com.PedroA10.Estufa.exception.UserNotFoundException;
import com.PedroA10.Estufa.mapper.UserMapper;
import com.PedroA10.Estufa.model.User;
import com.PedroA10.Estufa.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

  @Autowired
  UserRepository userRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

    public List<UserResponseDTO> findAll() {
      return userRepository.findAll()
        .stream()
        .map(UserMapper::toDto)
        .collect(Collectors.toList());
    }
    
    public Optional<UserResponseDTO> findById(Long id) {
      return userRepository.findById(id)
        .map(UserMapper::toDto);
    }

    public void deleteById(Long id) {
      userRepository.deleteById(id);
    }

    public UserResponseDTO createUser(@Valid UserRequestDTO userRequestDTO) {
      if (userRequestDTO.getEmail().isEmpty()) {
        throw new EmailNotFoundException("Email is required.");
      }
      if (userRepository.existsByEmail(userRequestDTO.getEmail())) {
        throw new UserAlreadyExistsException("User with this email already exists." + userRequestDTO.getEmail());
      }

      User user = UserMapper.toModel(userRequestDTO);

      if (userRequestDTO.getPassword() == null || userRequestDTO.getPassword().isEmpty()) {
        throw new IllegalArgumentException("Password is required.");
      }

      user.setPassword(passwordEncoder.encode(user.getPassword()));

      User save =userRepository.save(user);
      return UserMapper.toDto(save);
    }

  public User findEntityById(Long id) {
    return userRepository.findById(id)
      .orElseThrow(() -> new UserNotFoundException("User not found"));
  }
}
