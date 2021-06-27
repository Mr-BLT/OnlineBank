package com.online.demo.repository;

import com.online.demo.model.database.StockAndUser;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockAndUserRepository extends JpaRepository<StockAndUser, Long> {

  Optional<StockAndUser> findByAccountBank_IdAndStockId(long accountBankId, long stockId);

  List<StockAndUser> findByAccountBank_Id(long accountBankId);
}
