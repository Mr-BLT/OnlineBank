package com.online.demo.service.Impl;

import com.online.demo.model.User;
import com.online.demo.model.User.State;
import com.online.demo.repository.UserRepository;
import com.online.demo.service.AdminService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {

  @Autowired
  public UserRepository userRepository;

  @Override
  public void ban(Long id) {
    System.out.println(id);
    User user = userRepository.findById(id)
        .orElseThrow(() -> new UsernameNotFoundException("User not found !!!"));
    user.setState(State.BANNED);
    userRepository.save(user);
  }

  @Override
  public void unban(Long id) {
    User user = userRepository.findById(id)
        .orElseThrow(() -> new UsernameNotFoundException("User not found !!!"));
    user.setState(State.ACTIVE);
    userRepository.save(user);
  }

  @Override
  public List<User> findAllUsers() {
    List<User> users = userRepository.findAll();
    return users;
  }
}
