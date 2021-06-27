package com.online.demo.service.Impl;

import com.online.demo.model.AccountBank;
import com.online.demo.model.User;
import com.online.demo.model.database.HistoryUserTransaction;
import com.online.demo.repository.AccountBankRepository;
import com.online.demo.repository.HistoryAccountBankTransactionRepository;
import com.online.demo.service.TransactionService;
import com.online.demo.service.UserService;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TransactionServiceImpl implements TransactionService {

  @Autowired
  private UserService userService;

  @Autowired
  private AccountBankRepository accountBankRepository;

  @Autowired
  private HistoryAccountBankTransactionRepository historyAccountBankTransactionRepository;

  @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
  @Override
  public int transactiobyNumber(String number, User userFrom, Long suma) {
    User userTo = userService.getUserByNumber(number);
    if (userTo == null) {
      return 0;
    }

    AccountBank accountBankTo = userTo.getAccountBankList();
    AccountBank accountBankForm = userFrom.getAccountBankList();
    if (accountBankForm.getSuma() - suma < 0) {
      return -1;
    }

    accountBankForm.setSuma(accountBankForm.getSuma() - suma);
    accountBankTo.setSuma(accountBankTo.getSuma() + suma);

    accountBankRepository.save(accountBankForm);
    accountBankRepository.save(accountBankTo);

    HistoryUserTransaction historyUserTransaction = HistoryUserTransaction.builder()
        .accountBankTo(accountBankTo)
        .accountBankForm(accountBankForm)
        .suma(suma)
        .date(new Date().toString())
        .build();

    historyAccountBankTransactionRepository.save(historyUserTransaction);

    return 1;
  }
}
