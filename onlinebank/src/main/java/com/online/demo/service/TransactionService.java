package com.online.demo.service;

import com.online.demo.model.User;

public interface TransactionService {

  public int transactiobyNumber(String number, User userFrom, Long suma);
}
