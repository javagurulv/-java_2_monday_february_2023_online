package com.fightclub.core.service;

import com.fightclub.core.domain.User;
import com.fightclub.core.domain.command.RegisterCommand;
import com.fightclub.core.domain.command.RegisterResult;
import com.fightclub.core.inbound.RegisterUserUseCase;
import com.fightclub.core.outbound.SaveUserPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RegistrationService implements RegisterUserUseCase {

    private final ValidationService validationService;
    private final PasswordHashingService passwordHashingService;
    private final SaveUserPort saveUserPort;


    @Override
    public RegisterResult register(RegisterCommand command) {

        validationService.validateName(command.getName());
        validationService.validateEmail(command.getEmail());
        validationService.validatePassword(command.getPassword(), command.getRepeatPassword());
        String hashPassword = passwordHashingService.hashPassword(command.getPassword());
        User user = buildUser(command, hashPassword);
        Long userId = saveUserPort.save(user);
        return new RegisterResult(userId);
    }

    private User buildUser(RegisterCommand command, String hashPassword) {

        return new User(null, command.getName(), hashPassword, command.getEmail(), List.of());
    }

}
