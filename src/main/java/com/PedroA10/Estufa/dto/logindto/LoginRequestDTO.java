package com.PedroA10.Estufa.dto.logindto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequestDTO {

  @NotBlank(message = "Username is required")
  @Size(min = 5, max = 20, message = "Username must be between 5 and 20 charact4ers.")
  private String username;

  @NotBlank(message = "Password is required")
  private String password;
}
