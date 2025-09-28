package com.PedroA10.Estufa.repository;

import com.PedroA10.Estufa.model.Taxonomy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaxonomyRepository extends JpaRepository <Taxonomy, Long> {

  Optional<Taxonomy> findById(Long id);

  Optional<List<Taxonomy>> findByUserUsername(String username);
}
