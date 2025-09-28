package com.PedroA10.Estufa.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Data
@AllArgsConstructor
public class Taxonomy {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String description;

  private byte[] image;

  @ManyToOne
  private User user;

  @ManyToOne
  @JoinColumn(name = "greenhouse_id")
  private Greenhouse greenhouse;
}
