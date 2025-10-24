package com.PedroA10.Estufa.dto.logindto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class LoginResponseDTO {

  private String accessToken;
  private String tokenType = "Bearer";
}
