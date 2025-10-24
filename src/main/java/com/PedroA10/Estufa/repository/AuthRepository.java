package com.PedroA10.Estufa.repository;

import com.PedroA10.Estufa.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthRepository extends JpaRepository<User, Long> {

  User findUserByEmail(String email);
}
