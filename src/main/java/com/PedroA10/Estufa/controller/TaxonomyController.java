package com.PedroA10.Estufa.controller;

import com.PedroA10.Estufa.dto.taxondto.TaxonRequestDTO;
import com.PedroA10.Estufa.dto.taxondto.TaxonResponseDTO;
import com.PedroA10.Estufa.service.TaxonomyService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/taxon")
public class TaxonomyController {

  @Autowired
  TaxonomyService taxonomyService;

  @GetMapping
  public List<TaxonResponseDTO> listTaxon() {
    return taxonomyService.findAll();
  }

  @GetMapping("/{id}")
  public ResponseEntity<TaxonResponseDTO> listTaxonById(@PathVariable Long id) {
    return taxonomyService.findById(id)
      .map(ResponseEntity::ok)
      .orElse(ResponseEntity.notFound().build());
  }

  @PostMapping
  public ResponseEntity<TaxonResponseDTO> createTaxon(@RequestBody @Valid TaxonRequestDTO taxonRequestDTO) {
    try {
      TaxonResponseDTO newTaxon = taxonomyService.createTaxon(taxonRequestDTO);
      return new ResponseEntity<>(newTaxon, HttpStatus.CREATED);
    } catch (IllegalArgumentException e) {
      return ResponseEntity.badRequest().build();
    }
  }


  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteTaxon(@PathVariable Long id) {
    try {
      taxonomyService.deleteTaxonById(id);
      return ResponseEntity.noContent().build();
    }catch (IllegalArgumentException e) {
      return ResponseEntity.notFound().build();
    }
  }
}
