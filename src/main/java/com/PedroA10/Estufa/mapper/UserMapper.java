package com.PedroA10.Estufa.mapper;

import com.PedroA10.Estufa.dto.userdto.UserRequestDTO;
import com.PedroA10.Estufa.dto.userdto.UserResponseDTO;
import com.PedroA10.Estufa.model.User;
import jakarta.validation.Valid;

public class UserMapper {

  public static UserResponseDTO toDto (User user) {
    UserResponseDTO dto = new UserResponseDTO();
    dto.setId(user.getId());
    dto.setName(user.getEmail());
    dto.setEmail(user.getEmail());
    return dto;
  }

  public static User toModel (@Valid UserRequestDTO dto) {
  User user = new User();
  user.setUsername(dto.getName());
  user.setEmail(dto.getEmail());
  user.setPassword(dto.getPassword());
  return user;
  }
}
