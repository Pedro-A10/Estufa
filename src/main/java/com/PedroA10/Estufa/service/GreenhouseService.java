package com.PedroA10.Estufa.service;

import com.PedroA10.Estufa.dto.greenhousedto.GreenhouseResponseDto;
import com.PedroA10.Estufa.mapper.GreenhouseMapper;
import com.PedroA10.Estufa.repository.GreenhouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GreenhouseService {

  @Autowired
  GreenhouseRepository greenhouseRepository;

  public List<GreenhouseResponseDto> findAll() {
    return greenhouseRepository.findAll()
      .stream()
      .map(GreenhouseMapper::toDto)
      .collect(Collectors.toList());
  }

  public Optional<GreenhouseResponseDto> findById(Long id) {
    return greenhouseRepository.findById(id)
      .map(GreenhouseMapper::toDto);
  }

  public Optional<GreenhouseResponseDto> findByUsername(String username) {
    return greenhouseRepository.findByUserUsername(username)
      .map(GreenhouseMapper::toDto);
  }
}
