package com.PedroA10.Estufa.controller;

import com.PedroA10.Estufa.model.Taxonomy;
import com.PedroA10.Estufa.model.User;
import com.PedroA10.Estufa.service.TaxonomyService;
import com.PedroA10.Estufa.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/taxon")
public class TaxonomyController {

  @Autowired
  TaxonomyService taxonomyService;

  @Autowired
  private UserService userService;

  @GetMapping
  public List<Taxonomy> listTaxon() {
    return taxonomyService.findAll();
  }

  @GetMapping
  public Optional<Taxonomy> listTaxonById(@PathVariable Long id) {
    return taxonomyService.findById(id);
  }

  @PostMapping
  public ResponseEntity<Taxonomy> createTaxon(@RequestBody @Valid Taxonomy taxonomy) {
    Taxonomy newTaxon = taxonomyService.createTaxon(taxonomy);
    return new ResponseEntity<>(newTaxon, HttpStatus.CREATED);
  }

  @DeleteMapping("{/id}")
  public ResponseEntity<Void> deleteTaxon(@PathVariable Long id) {
    try {
      taxonomyService.deleteTaxonById(id);
      return ResponseEntity.noContent().build();
    }catch (IllegalArgumentException e) {
      return ResponseEntity.notFound().build();
    }
  }
}
