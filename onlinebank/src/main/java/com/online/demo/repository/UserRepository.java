package com.online.demo.repository;

import com.online.demo.model.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

  Optional<User> findByNumber(String number);

  Optional<User> findById(Long id);

  User getByNumber(String number);

  User getById(Long id);
}
