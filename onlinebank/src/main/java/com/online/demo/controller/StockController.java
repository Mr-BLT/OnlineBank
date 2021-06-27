package com.online.demo.controller;

import com.online.demo.model.AccountBank;
import com.online.demo.model.Stock;
import com.online.demo.model.User;
import com.online.demo.service.StockService;
import com.online.demo.service.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@PreAuthorize("hasAnyAuthority('USER')")
public class StockController {

  @Autowired
  private StockService stockService;

  @Autowired
  private UserService userService;

  @GetMapping("/stock")
  public String getStockPage(Authentication authentication, Model model) {

    User user = userService.getUser(Long.valueOf(authentication.getName()));
    AccountBank accountBank = userService.getAccountBank(user.getAccountBankList().getId());

    model.addAttribute("accountbank", accountBank);

    return "stock_page";
  }

  @GetMapping("/stock/buy")
  public String getStockBuyPage(Model model, Authentication authentication) {

    User user = userService.getUser(Long.valueOf(authentication.getName()));
    AccountBank accountBank = userService.getAccountBank(user.getAccountBankList().getId());

    model.addAttribute("accountbank", accountBank);
    model.addAttribute("stocks", stockService.getStockList());

    return "stock_buy_page";
  }

  @GetMapping("/stock/sell")
  public String getStockSellPage(Model model, Authentication authentication) {

    User user = userService.getUser(Long.valueOf(authentication.getName()));
    AccountBank accountBank = userService.getAccountBank(user.getAccountBankList().getId());

    model.addAttribute("accountbank", accountBank);
    model.addAttribute("stocks", stockService.getUserStockList(user.getId()));

    return "stock_sell_page";
  }


  @PostMapping("/stock/buy")
  public ResponseEntity getStockBuy(Authentication authentication,
      @RequestParam("id") Long stockId,
      @RequestParam("count") Long count) {
    Long userId = Long.parseLong(authentication.getName());

    long result = stockService.buyStock(userId, stockId, count);

    if (result == 1) {
      return ResponseEntity.ok(userService.getUser(userId).getAccountBankList().getSuma());
    } else if (result == -1) {
      return ResponseEntity.badRequest().build();
    }
    return ResponseEntity.status(404).build();
  }

  @PostMapping("/stock/sell")
  public ResponseEntity getStockSell(Authentication authentication,
      @RequestParam("id") Long stockId,
      @RequestParam("count") Long count) {

    Long userId = Long.parseLong(authentication.getName());

    int result = stockService.sellStock(userId, stockId, count);

    if (result == 1) {
      return ResponseEntity.ok(userService.getUser(userId).getAccountBankList().getSuma());
    } else {
      return ResponseEntity.badRequest().build();
    }
  }

  @PostMapping("/stock/find")
  public ResponseEntity findStoksByName(@RequestParam("id") Long stockId) {
    return ResponseEntity.ok(stockService.getStockListLike(stockId));
  }

  @GetMapping("/stock/list")
  public List<Stock> getPostStockList() {
    return stockService.getStockList();
  }

}
