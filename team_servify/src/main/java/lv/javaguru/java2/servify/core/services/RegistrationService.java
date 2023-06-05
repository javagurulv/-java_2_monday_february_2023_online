package lv.javaguru.java2.servify.core.services;

import lv.javaguru.java2.servify.core.database.jpa.JpaUserRepository;
import lv.javaguru.java2.servify.core.database.jpa.JpaUserTypeRepository;
import lv.javaguru.java2.servify.core.domain.UserEntity;
import lv.javaguru.java2.servify.core.domain.UserType;
import lv.javaguru.java2.servify.core.dto.RegistrationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
@Transactional
public class RegistrationService {
    @Autowired
    private JpaUserRepository userRepository;
    @Autowired
    private JpaUserTypeRepository roleRepository;

    @Autowired
    private PasswordEncoder encoder;

    public UserEntity registerUser(RegistrationDTO registrDTO) {
        String ensodedPassword = encoder.encode(registrDTO.getPassword());
        UserType userRole = roleRepository.findByAuthority("CUSTOMER").get();
        Set<UserType> authorities = new HashSet<>();
        authorities.add(userRole);
        UserEntity newUser = new UserEntity(
                registrDTO.getFirstName(),
                registrDTO.getLastName(),
                registrDTO.getEmail(),
                registrDTO.getPhoneNumber(),
                ensodedPassword,
                authorities);
        return userRepository.save(newUser);
    }
}
