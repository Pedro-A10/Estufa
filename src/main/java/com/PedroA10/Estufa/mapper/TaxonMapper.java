package com.PedroA10.Estufa.mapper;

import com.PedroA10.Estufa.dto.taxondto.TaxonRequestDTO;
import com.PedroA10.Estufa.dto.taxondto.TaxonResponseDTO;
import com.PedroA10.Estufa.model.Greenhouse;
import com.PedroA10.Estufa.model.Taxonomy;
import com.PedroA10.Estufa.model.User;

import java.util.Base64;

public class TaxonMapper {

  public static TaxonResponseDTO toDto(Taxonomy taxon) {
    if (taxon == null) return null;

    TaxonResponseDTO dto = new TaxonResponseDTO();
    dto.setId(taxon.getId());
    dto.setDescription(taxon.getDescription());
    dto.setUserId(taxon.getUser().getId());
    dto.setGreenhouseId(taxon.getGreenhouse() != null ? taxon.getGreenhouse().getId() : null);

    if (taxon.getImage() != null) {
      dto.setBase64Image(Base64.getEncoder().encodeToString(taxon.getImage()));
    }
    return dto;
  }

  private static Taxonomy toModel(TaxonRequestDTO dto, User user, Greenhouse greenhouse) {
    if (dto == null) return null;

    Taxonomy taxonomy = new Taxonomy();
    taxonomy.setDescription(dto.getDescription());
    taxonomy.setImage(dto.getImage());
    taxonomy.setUser(user);
    taxonomy.setGreenhouse(greenhouse);

    return taxonomy;
  }
}
