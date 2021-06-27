package com.online.demo.service;

import com.online.demo.model.User;
import java.util.List;

public interface AdminService {

  public void ban(Long id);

  public void unban(Long id);

  public List<User> findAllUsers();
}
