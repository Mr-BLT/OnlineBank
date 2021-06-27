package com.online.demo.service;

import com.online.demo.model.Subscription;
import java.util.List;

public interface SubscriptionService {
  public List<Subscription> getAllSubscb();
  public List<Subscription> getUserSubscb(Long userId);
  public int subscribe(Long subscriptionId, Long userId);
  public int unsubscribe(Long id, Long userId);
}
