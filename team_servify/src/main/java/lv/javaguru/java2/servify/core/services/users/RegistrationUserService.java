package lv.javaguru.java2.servify.core.services.users;

import lv.javaguru.java2.servify.core.database.jpa.JpaUserRepository;
import lv.javaguru.java2.servify.core.database.jpa.JpaUserTypeRepository;
import lv.javaguru.java2.servify.core.domain.UserEntity;
import lv.javaguru.java2.servify.core.domain.UserType;
import lv.javaguru.java2.servify.core.dto.RegistrationDTO;
import lv.javaguru.java2.servify.core.dto.responses.AddUserResponse;
import lv.javaguru.java2.servify.core.dto.responses.CoreError;
import lv.javaguru.java2.servify.core.services.validators.RegistrationUserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class RegistrationUserService {
    @Autowired
    private JpaUserRepository userRepository;
    @Autowired
    private JpaUserTypeRepository roleRepository;
    @Autowired
    private RegistrationUserValidator validator;


    public AddUserResponse registerUser(RegistrationDTO registrDTO) {
        List<CoreError> errors = validator.validate(registrDTO);
        if (!errors.isEmpty()) {
            return new AddUserResponse(errors);
        }
        UserType userRole = roleRepository.findByAuthority("CUSTOMER").get();
        Set<UserType> authorities = new HashSet<>();
        authorities.add(userRole);
        UserEntity newUser = new UserEntity(
                registrDTO.getFirstName(),
                registrDTO.getLastName(),
                registrDTO.getEmail(),
                registrDTO.getPhoneNumber(),
                registrDTO.getPassword(),
                authorities);
        userRepository.save(newUser);
        return new AddUserResponse(true);
    }
}
