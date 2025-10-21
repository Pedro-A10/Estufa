package com.PedroA10.Estufa.dto.taxondto;

import lombok.Data;

@Data
public class TaxonRequestDTO {

  private String description;
  private byte[] image;
  private Long userId;
  private Long greenhouseId;
}
