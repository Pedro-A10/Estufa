package com.PedroA10.Estufa.dto.taxondto;

import lombok.Data;

@Data
public class TaxonResponseDTO {

  private Long id;
  private String description;
  private String base64Image;
  private Long userId;
  private Long greenhouseId;
}
