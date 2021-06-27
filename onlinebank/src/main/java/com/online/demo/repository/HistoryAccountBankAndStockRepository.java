package com.online.demo.repository;

import com.online.demo.model.database.HistoryStockAndUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoryAccountBankAndStockRepository extends JpaRepository<HistoryStockAndUser, Long> {

}
