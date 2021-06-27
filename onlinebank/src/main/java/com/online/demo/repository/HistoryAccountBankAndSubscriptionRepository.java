package com.online.demo.repository;

import com.online.demo.model.database.HistoryUserAndSubscription;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoryAccountBankAndSubscriptionRepository extends
    JpaRepository<HistoryUserAndSubscription, Long> {

}
