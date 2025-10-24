package com.PedroA10.Estufa.service;

import com.PedroA10.Estufa.dto.registerdto.RegisterRequestDTO;
import com.PedroA10.Estufa.dto.registerdto.RegisterResponseDTO;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService{

  @Override
  public RegisterResponseDTO register(RegisterRequestDTO registerRequestDTO) {
    return new RegisterResponseDTO();
  }
}
