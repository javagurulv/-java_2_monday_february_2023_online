package com.fightclub.core.service;

import com.fightclub.core.domain.User;
import com.fightclub.core.domain.error.AuthenticationException;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    public void authenticate(String hashPassword, User user) {

        boolean isPasswordsEquals = user.getHashPassword().equals(hashPassword);

        if (!isPasswordsEquals) {
            throw new AuthenticationException("ERROR!!! Passwords are not The same!");
        }

    }
}
