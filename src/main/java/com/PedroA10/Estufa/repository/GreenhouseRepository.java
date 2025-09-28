package com.PedroA10.Estufa.repository;

import com.PedroA10.Estufa.model.Greenhouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GreenhouseRepository extends JpaRepository<Greenhouse, Long> {

  Optional<List<Greenhouse>> findByUserUsername(String username);

  List<Greenhouse> listGreenhousePublic();

  List<Greenhouse> listGreenhousePrivate();
}
