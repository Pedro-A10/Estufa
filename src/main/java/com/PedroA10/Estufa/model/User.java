package com.PedroA10.Estufa.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotEmpty
  @Size(min = 5, max = 20, message = "Username must be 5 to 20 characters long")
  private String username;

  @Email
  @NotEmpty
  private String email;

  @NotEmpty
  @Size(min = 8, max = 100, message = "Password must be 8 to more characters")
  private String password;

  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Taxonomy> taxonomies;
}
