package com.PedroA10.Estufa.service;

import com.PedroA10.Estufa.model.Greenhouse;
import com.PedroA10.Estufa.model.Taxonomy;
import com.PedroA10.Estufa.repository.GreenhouseRepository;
import com.PedroA10.Estufa.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GreenhouseService {

  @Autowired
  UserRepository userRepository;

  @Autowired
  GreenhouseRepository greenhouseRepository;

  public List<Greenhouse> findAll() {
    return greenhouseRepository.findAll();
  }

  public Optional<Greenhouse> findById(Long id) {
    return greenhouseRepository.findById(id);
  }


  public Optional<List<Greenhouse>> findByUsername(String username) {
    return greenhouseRepository.findByUserUsername(username);
  }

}
