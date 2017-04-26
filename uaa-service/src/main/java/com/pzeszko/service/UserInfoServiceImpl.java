package com.pzeszko.service;

import com.pzeszko.repository.UserTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Admin on 26.04.2017.
 */
@Service
@Transactional(readOnly = true)
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserTokenRepository userTokenRepository;

    @Override
    public String getUsername(String token) {
        return userTokenRepository.findByAccess_Token(token).getUsername();
    }
}
