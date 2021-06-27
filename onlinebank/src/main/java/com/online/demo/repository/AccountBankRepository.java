package com.online.demo.repository;

import com.online.demo.model.AccountBank;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountBankRepository extends JpaRepository<AccountBank,Long> {
  Optional<AccountBank> findById(Long id);
}
