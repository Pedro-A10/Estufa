package com.PedroA10.Estufa.service;

import com.PedroA10.Estufa.model.Taxonomy;
import com.PedroA10.Estufa.repository.TaxonomyRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaxonomyService {

  @Autowired
  TaxonomyRepository taxonomyRepository;

  public List<Taxonomy> findAll() {
    return taxonomyRepository.findAll();
  }

  public Optional<Taxonomy> findById(Long id) {
    return taxonomyRepository.findById(id);
  }

  public Optional<List<Taxonomy>> findByUsername(String username) {
    return taxonomyRepository.findByUserUsername(username);
  }

  public void deleteTaxonById(Long id) {
    taxonomyRepository.deleteById(id);
  }

  public Taxonomy createTaxon(@Valid Taxonomy taxonomy) {
    return taxonomyRepository.save(taxonomy);
  }
}
