package com.online.demo.service.Impl;

import com.online.demo.model.AccountBank;
import com.online.demo.model.Subscription;
import com.online.demo.model.User;
import com.online.demo.model.database.HistoryUserAndSubscription;
import com.online.demo.model.database.UserAndSubscription;
import com.online.demo.repository.AccountBankRepository;
import com.online.demo.repository.HistoryAccountBankAndSubscriptionRepository;
import com.online.demo.repository.SubscriptionRepositoryRepositopry;
import com.online.demo.repository.UserAndSubscriptionRepository;
import com.online.demo.repository.UserRepository;
import com.online.demo.service.SubscriptionService;
import com.online.demo.service.UserService;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private SubscriptionRepositoryRepositopry subscriptionRepository;

  @Autowired
  private AccountBankRepository accountBankRepository;

  @Autowired
  private UserAndSubscriptionRepository userAndSubscriptionRepository;

  @Autowired
  private UserService userService;

  @Autowired
  private HistoryAccountBankAndSubscriptionRepository historyAccountBankAndSubscriptionRepository;

  @Override
  public List<Subscription> getAllSubscb() {
    return subscriptionRepository.findAll();
  }

  @Override
  public List<Subscription> getUserSubscb(Long userId) {

    User user = userService.getUser(userId);

    List<UserAndSubscription> userAndSubscriptions = userAndSubscriptionRepository
        .findByAccountBank_Id(user.getAccountBankList().getId());

    List<Subscription> subscriptions = new ArrayList<>();

    for (int i = 0; i < userAndSubscriptions.size(); i++) {
      subscriptions
          .add(subscriptionRepository
              .getById(userAndSubscriptions.get(i).getSubscription().getId()));
    }

    return subscriptions;
  }


  @Override
  public int subscribe(Long subscriptionId, Long userId) {
    Subscription subscription = subscriptionRepository.getById(subscriptionId);
    User user = userRepository.getById(userId);

    if (user == null || subscription == null) {
      return -1;
    }

    AccountBank accountBank = user.getAccountBankList();

    if (accountBank.getSuma() < subscription.getPrice()) {
      return 0;
    }

    accountBank.setSuma(accountBank.getSuma() - subscription.getPrice());
    accountBankRepository.save(accountBank);

    UserAndSubscription userAndSubscription =
        userAndSubscriptionRepository
            .findByAccountBank_IdAndSubscription_Id(user.getAccountBankList().getId(),
                subscriptionId)
            .orElse(UserAndSubscription.builder()
                .accountBank(user.getAccountBankList())
                .subscription(subscriptionRepository.getById(subscriptionId))
                .start(LocalDate.now().toString())
                .build());

    userAndSubscriptionRepository.save(userAndSubscription);

    return 1;
  }

  @Override
  public int unsubscribe(Long id, Long userId) {
    Subscription subscription = subscriptionRepository.getById(id);
    User user = userRepository.getById(userId);

    if (user == null || subscription == null) {
      return -1;
    }

    UserAndSubscription userAndSubscription = userAndSubscriptionRepository
        .getByAccountBank_IdAndSubscriptionId(user.getAccountBankList().getId(), id);

    HistoryUserAndSubscription historyUserAndSubscription = HistoryUserAndSubscription.builder()
        .accountBank(user.getAccountBankList())
        .subscription(subscription)
        .start(userAndSubscription.getStart())
        .finish(LocalDate.now().toString())
        .build();

    historyAccountBankAndSubscriptionRepository.save(historyUserAndSubscription);

    userAndSubscriptionRepository.delete(userAndSubscription);

    return 1;
  }
}
