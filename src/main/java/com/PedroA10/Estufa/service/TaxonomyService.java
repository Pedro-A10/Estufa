package com.PedroA10.Estufa.service;

import com.PedroA10.Estufa.dto.taxondto.TaxonRequestDTO;
import com.PedroA10.Estufa.dto.taxondto.TaxonResponseDTO;
import com.PedroA10.Estufa.exception.UserNotFoundException;
import com.PedroA10.Estufa.mapper.TaxonMapper;
import com.PedroA10.Estufa.model.Greenhouse;
import com.PedroA10.Estufa.model.Taxonomy;
import com.PedroA10.Estufa.model.User;
import com.PedroA10.Estufa.repository.GreenhouseRepository;
import com.PedroA10.Estufa.repository.TaxonomyRepository;
import com.PedroA10.Estufa.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TaxonomyService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private GreenhouseRepository greenhouseRepository;

  @Autowired
  TaxonomyRepository taxonomyRepository;

  public List<TaxonResponseDTO> findAll() {
    return taxonomyRepository.findAll()
      .stream()
      .map(TaxonMapper::toDto)
      .collect(Collectors.toList());
  }

  public Optional<TaxonResponseDTO> findById(Long id) {
    return taxonomyRepository.findById(id)
      .map(TaxonMapper::toDto);
  }

  public Optional<List<TaxonResponseDTO>> findByUsername(String username) {
    return taxonomyRepository.findByUserUsername(username)
      .map(taxonomies -> taxonomies.stream()
        .map(TaxonMapper::toDto)
        .collect(Collectors.toList()));
  }

  public void deleteTaxonById(Long id) {
    if (taxonomyRepository.existsById(id)) {
      taxonomyRepository.deleteById(id);
    }
  }

  public TaxonResponseDTO createTaxon(@Valid TaxonRequestDTO taxonRequestDTO) {
    User user = userRepository.findById(taxonRequestDTO.getUserId())
      .orElseThrow(() -> new UserNotFoundException("User not found"));

    Greenhouse greenhouse = greenhouseRepository.findByUserUsername(user.getUsername())
      .orElseThrow(() -> new IllegalArgumentException("Greenhouse not found for user"));

    Taxonomy entity = TaxonMapper.toModel(taxonRequestDTO, user, greenhouse);
    Taxonomy saved = taxonomyRepository.save(entity);
    return TaxonMapper.toDto(saved);
  }
}
