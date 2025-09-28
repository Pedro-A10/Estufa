package com.PedroA10.Estufa.repository;

import com.PedroA10.Estufa.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository <User, Long> {

  Optional<User> findById(Long id);

  Optional<User> findByUsername(String username);

  boolean existsByEmail(String email);
}
