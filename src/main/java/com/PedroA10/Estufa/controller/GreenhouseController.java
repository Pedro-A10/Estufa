package com.PedroA10.Estufa.controller;

import com.PedroA10.Estufa.dto.greenhousedto.GreenhouseResponseDto;
import com.PedroA10.Estufa.service.GreenhouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/greenhouse")
public class GreenhouseController {

  @Autowired
  GreenhouseService greenhouseService;

  @GetMapping
  public List<GreenhouseResponseDto> listGreenHouse() {
    return greenhouseService.findAll();
  }

  @GetMapping("/{id}")
  public ResponseEntity<GreenhouseResponseDto> listGreenHouseById(@PathVariable Long id) {
    return greenhouseService.findById(id)
      .map(ResponseEntity::ok)
      .orElse(ResponseEntity.notFound().build());
  }
}
