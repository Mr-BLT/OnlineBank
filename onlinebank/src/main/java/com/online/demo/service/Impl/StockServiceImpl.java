package com.online.demo.service.Impl;

import com.online.demo.model.AccountBank;
import com.online.demo.model.Stock;
import com.online.demo.model.User;
import com.online.demo.model.database.HistoryStockAndUser;
import com.online.demo.model.database.StockAndUser;
import com.online.demo.model.form.StockForm;
import com.online.demo.repository.AccountBankRepository;
import com.online.demo.repository.HistoryAccountBankAndStockRepository;
import com.online.demo.repository.StockAndUserRepository;
import com.online.demo.repository.StockRepository;
import com.online.demo.service.StockService;
import com.online.demo.service.UserService;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StockServiceImpl implements StockService {

  @Autowired
  private UserService userService;

  @Autowired
  private AccountBankRepository accountBankRepository;

  @Autowired
  private StockAndUserRepository stockAndUserRepository;

  @Autowired
  private HistoryAccountBankAndStockRepository historyAccountBankAndStockRepository;

  @Autowired
  private StockRepository stockRepository;

  @Override
  public int buyStock(Long userId, Long stockId, Long count) {
    User user = userService.getUser(userId);
    AccountBank accountBank = user.getAccountBankList();

    Stock stock = stockRepository.getById(stockId);

    if (stock == null) {
      return -1;
    }

    if (stock.getPrice() * count > accountBank.getSuma()) {
      return 0;
    }

    accountBank.setSuma(accountBank.getSuma() - count * stock.getPrice());

    StockAndUser stockAndUser = stockAndUserRepository
        .findByAccountBank_IdAndStockId(userId, stockId)
        .orElse(
            StockAndUser.builder().accountBank(accountBank)
                .stock(stockRepository.getById(stockId))
                .count(0L).build());

    stockAndUser.setCount(stockAndUser.getCount() + count);

    stockAndUserRepository.save(stockAndUser);

    HistoryStockAndUser historyStockAndUser = HistoryStockAndUser.builder()
        .accountBank(accountBank)
        .stock(stock)
        .time(LocalDate.now().toString())
        .status(1L)
        .count(count)
        .build();

    historyAccountBankAndStockRepository.save(historyStockAndUser);

    return 1;
  }

  @Override
  public int sellStock(Long userId, Long stockId, Long count) {
    User user = userService.getUser(userId);
    AccountBank accountBank = user.getAccountBankList();

    Stock stock = stockRepository.getById(stockId);

    if (stock == null) {
      return -1;
    }

    StockAndUser stockAndUser = stockAndUserRepository
        .findByAccountBank_IdAndStockId(userService.getUser(userId).getAccountBankList().getId(),
            stockRepository.getById(stockId).getId())
        .orElseThrow();

    if (stockAndUser.getCount() < count) {
      return -1;
    }

    accountBank.setSuma(accountBank.getSuma() + count * stock.getPrice());

    stockAndUser.setCount(stockAndUser.getCount() - count);

    if (stockAndUser.getCount() == 0) {
      stockAndUserRepository.deleteById(stockAndUser.getId());
    } else {
      stockAndUserRepository.save(stockAndUser);
    }

    HistoryStockAndUser historyStockAndUser = HistoryStockAndUser.builder()
        .accountBank(accountBank)
        .stock(stock)
        .time(LocalDate.now().toString())
        .status(0L)
        .count(count)
        .build();

    historyAccountBankAndStockRepository.save(historyStockAndUser);

    return 1;
  }

  @Override
  public List<Stock> getStockListLike(Long stockId) {
    Stock stock = stockRepository.getById(stockId);
    if (stock == null) {
      return new ArrayList<>();
    }
    List<Stock> stocks = stockRepository.getByNameLike(stock.getName());
    return stocks;
  }

  @Override
  public List<Stock> getStockList() {
    return stockRepository.findAll();
  }


  @Override
  public List<StockForm> getUserStockList(Long userId) {

    List<StockAndUser> stockAndUsers = stockAndUserRepository
        .findByAccountBank_Id(userService.getUser(userId).getAccountBankList().getId());
    List<StockForm> answerStock = new ArrayList<>();

    for (StockAndUser stock : stockAndUsers) {
      Long stockId = stock.getStock().getId();
      Stock stock1 = stockRepository.findById(stockId).orElseThrow();

      answerStock
          .add(
              new StockForm(stock1.getId(), stock1.getName(), stock1.getPrice(), stock.getCount()));
    }

    return answerStock;
  }


}
