package com.online.demo.secuirty.detail;

import com.online.demo.model.User;
import com.online.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("customUserDetailsService")
public class UserDetailsServiceImpl implements
    org.springframework.security.core.userdetails.UserDetailsService {

  @Autowired
  private UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String number) throws UsernameNotFoundException {
    User user = userRepository.findByNumber(number)
        .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    return new UserDetailsImpl(user);
  }
}
