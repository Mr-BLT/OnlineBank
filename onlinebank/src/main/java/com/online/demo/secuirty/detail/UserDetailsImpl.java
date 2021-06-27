package com.online.demo.secuirty.detail;

import com.online.demo.model.User;
import java.util.Collection;
import java.util.Collections;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserDetailsImpl implements UserDetails {

  private User user;

  public UserDetailsImpl(User user) {
    this.user = user;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    SimpleGrantedAuthority authority = new SimpleGrantedAuthority(user.getRole().toString());
    return Collections.singleton(authority);
  }

  @Override
  public String getPassword() {
    return user.getPassword();
  }

  @Override
  public String getUsername() {
    return String.valueOf(user.getId());
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return user.isActive();
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return user.isActive();
  }
}
