package com.PedroA10.Estufa.mapper;

import com.PedroA10.Estufa.dto.greenhousedto.GreenhouseRequestDTO;
import com.PedroA10.Estufa.dto.greenhousedto.GreenhouseResponseDTO;
import com.PedroA10.Estufa.dto.taxondto.TaxonResponseDTO;
import com.PedroA10.Estufa.model.Greenhouse;
import com.PedroA10.Estufa.model.User;

import java.util.List;
import java.util.stream.Collectors;

public class GreenhouseMapper {

  public static GreenhouseResponseDTO toDto(Greenhouse greenhouse) {
    GreenhouseResponseDTO dto = new GreenhouseResponseDTO();
    dto.setId(greenhouse.getId());

    if (greenhouse.getUser() != null) {
      dto.setUserId(greenhouse.getUser().getId());
    }

    dto.setPrivate(greenhouse.isPrivate());

    if (greenhouse.getTaxonomies() != null) {
      List<TaxonResponseDTO> taxonomyDto = greenhouse.getTaxonomies().stream()
        .map(TaxonMapper::toDto)
        .collect(Collectors.toList());
      dto.setTaxonomies(taxonomyDto);
    }
    return dto;
  }

  private static Greenhouse toModel(GreenhouseRequestDTO dto, User user) {
    Greenhouse greenhouse = new Greenhouse();

    greenhouse.setUser(user);
    greenhouse.setPrivate(dto.isPrivate());

    return greenhouse;
  }
}
