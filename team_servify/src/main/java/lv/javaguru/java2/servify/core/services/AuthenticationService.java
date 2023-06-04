package lv.javaguru.java2.servify.core.services;

import lv.javaguru.java2.servify.core.database.jpa.JpaUserRepository;
import lv.javaguru.java2.servify.core.database.jpa.JpaUserTypeRepository;
import lv.javaguru.java2.servify.core.domain.UserEntity;
import lv.javaguru.java2.servify.core.domain.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
@Transactional
public class AuthenticationService {
    @Autowired
    private JpaUserRepository userRepository;
    @Autowired
    private JpaUserTypeRepository roleRepository;

    @Autowired
    private PasswordEncoder encoder;

    public UserEntity registerUser(String userName, String password) {
        String ensodedPassword = encoder.encode(password);
        UserType userRole = roleRepository.findByAuthority("CUSTOMER").get();
        Set<UserType> authorities = new HashSet<>();
        return userRepository.save(new UserEntity(userName, ensodedPassword, authorities));
    }
}
