package com.PedroA10.Estufa.dto.greenhousedto;

import com.PedroA10.Estufa.dto.taxondto.TaxonResponseDTO;
import lombok.Data;

import java.util.List;

@Data
public class GreenhouseResponseDto {

  private Long id;
  private Long userId;
  private boolean isPrivate;
  private List<TaxonResponseDTO> taxonomies;
}
