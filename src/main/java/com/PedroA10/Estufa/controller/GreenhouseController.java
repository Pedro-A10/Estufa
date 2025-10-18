package com.PedroA10.Estufa.controller;

import com.PedroA10.Estufa.model.Greenhouse;
import com.PedroA10.Estufa.service.GreenhouseService;
import com.PedroA10.Estufa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/greenhouse")
public class GreenhouseController {

  @Autowired
  GreenhouseService greenhouseService;

  @Autowired
  UserService userService;

  @GetMapping
  public List<Greenhouse> listGreenHouse() {
    return greenhouseService.findAll();
  }

  @GetMapping
  public Optional<Greenhouse> listGreenHouseById(@PathVariable Long id) {
    return greenhouseService.findById(id);
  }
}
