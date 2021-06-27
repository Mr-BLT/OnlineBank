package com.online.demo.repository;

import com.online.demo.model.database.UserFriendNumbers;
import java.util.List;
import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserFriendNumbersRepository extends JpaRepository<UserFriendNumbers, Long> {

  @Query(nativeQuery = true,
      value = "select DISTINCT * from user_friend_numbers usfm where usfm.user_id  =:userId order by id desc limit :limit")
  public List<UserFriendNumbers> getUserFriendNumbersBy(Long userId, Long limit);

}
