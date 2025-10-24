package com.PedroA10.Estufa.service;

import com.PedroA10.Estufa.dto.registerdto.RegisterRequestDTO;
import com.PedroA10.Estufa.dto.registerdto.RegisterResponseDTO;

public interface AuthService {

  RegisterResponseDTO register(RegisterRequestDTO registerRequestDTO);
}
