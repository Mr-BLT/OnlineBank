package com.online.demo.repository;

import com.online.demo.model.database.HistoryUserTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoryAccountBankTransactionRepository extends
    JpaRepository<HistoryUserTransaction, Long> {

}
