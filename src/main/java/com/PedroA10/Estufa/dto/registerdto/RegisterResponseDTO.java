package com.PedroA10.Estufa.dto.registerdto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterResponseDTO {
  private Long id;
  private String username;
  private String email;
}
