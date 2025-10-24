package com.PedroA10.Estufa.controller;

import com.PedroA10.Estufa.dto.ResponseHandler;
import com.PedroA10.Estufa.dto.logindto.LoginRequestDTO;
import com.PedroA10.Estufa.dto.logindto.LoginResponseDTO;
import com.PedroA10.Estufa.dto.registerdto.RegisterRequestDTO;
import com.PedroA10.Estufa.security.JWTService;
import com.PedroA10.Estufa.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

  private final JWTService jwtService;
  private final AuthenticationManager authenticationManager;
  private final AuthService authService;

  @Autowired
  public AuthController(AuthService authService, JWTService jwtService, AuthenticationManager authenticationManager) {
    this.authService = authService;
    this.jwtService = jwtService;
    this.authenticationManager = authenticationManager;
  }


  @PostMapping("/register")
  public ResponseEntity<Object> register(@Valid @RequestBody RegisterRequestDTO registerRequestDTO) {
    return ResponseHandler.generateResponse("User register successfully", HttpStatus.CREATED, authService.register(registerRequestDTO));
  }

  @PostMapping("/login")
  public ResponseEntity<Object> login(@Valid @RequestBody LoginRequestDTO loginRequestDTO) {
    try {
      Authentication authentication = authenticationManager
        .authenticate(new UsernamePasswordAuthenticationToken(loginRequestDTO.getUsername(), loginRequestDTO.getPassword()));
      UserDetails userDetails = (UserDetails) authentication.getPrincipal();
      String jwt = jwtService.generateToken(userDetails.getUsername());
      LoginResponseDTO loginResponseDTO = LoginResponseDTO.builder()
        .accessToken(jwt)
        .build();

      return ResponseHandler.generateResponse(
        "User logged in successfully", HttpStatus.OK, loginResponseDTO
      );
    }catch (Exception e){
      return new ResponseEntity<>("Incorrect username or password", HttpStatus.UNAUTHORIZED);
    }
  }
}
