package com.PedroA10.Estufa.config;

import com.PedroA10.Estufa.security.JWTFilter;
import com.PedroA10.Estufa.security.JWTService;
import com.PedroA10.Estufa.service.CustomUserDetailsService;
import com.PedroA10.Estufa.utils.ApplicationConstants;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

  private final CustomUserDetailsService customUserDetailsService;
  private final JWTService jwtService;

  public SecurityConfig(CustomUserDetailsService customUserDetailsService, JWTService jwtService) {
    this.customUserDetailsService = customUserDetailsService;
    this.jwtService = jwtService;
  }

  @Bean
  public JWTFilter jwtFilter() {
    return new JWTFilter(customUserDetailsService, jwtService);
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http.csrf(csrf -> csrf.disable())
      .authorizeHttpRequests(auth -> auth
        .requestMatchers(ApplicationConstants.PUBLIC_URLS).permitAll()
        .requestMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll()
        .requestMatchers(HttpMethod.GET, "/api/test/public/hello/**").hasAnyRole("USER", "ADMIN")
        .requestMatchers("/api/test/private/**").hasRole("ADMIN")
        .anyRequest().authenticated()
      )
      .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
      .addFilterBefore(jwtFilter(), UsernamePasswordAuthenticationFilter.class);

    return http.build();
  }

  @Bean
  public CorsConfigurationSource corsConfigurationSource() {
    CorsConfiguration configuration = new CorsConfiguration();
    configuration.setAllowedOriginPatterns(List.of("*"));
    configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS", "HEAD"));
    configuration.setAllowedHeaders(List.of("*"));
    configuration.setAllowCredentials(false);

    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", configuration);
    return source;
  }

  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration auth) throws Exception {
    return auth.getAuthenticationManager();
  }
}

