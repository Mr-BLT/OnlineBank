package com.online.demo.repository;

import com.online.demo.model.database.UserAndSubscription;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAndSubscriptionRepository extends JpaRepository<UserAndSubscription, Long> {


  UserAndSubscription getByAccountBank_IdAndSubscriptionId(Long accountBankId, Long subscriptionId);

  Optional<UserAndSubscription> findByAccountBank_IdAndSubscription_Id(Long accountBankId,
      Long subscriptionId);

  List<UserAndSubscription> findByAccountBank_Id(Long accountBankId);
}
