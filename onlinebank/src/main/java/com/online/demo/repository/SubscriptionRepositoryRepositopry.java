package com.online.demo.repository;

import com.online.demo.model.Subscription;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscriptionRepositoryRepositopry extends JpaRepository<Subscription, Long> {
    Subscription getById(Long id);
}
