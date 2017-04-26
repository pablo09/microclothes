package com.pzeszko.repository;

import com.pzeszko.model.UserToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by Admin on 26.04.2017.
 */
@Repository
public interface UserTokenRepository extends JpaRepository<UserToken, Long> {

    UserToken findByUsername(String username);

    @Query("FROM UserToken uT WHERE uT.access_token = :access_token")
    UserToken findByAccess_Token(@Param("access_token")String access_token);
}
