package com.fightclub.core.service;

import com.fightclub.core.domain.User;
import com.fightclub.core.domain.command.LoginCommand;
import com.fightclub.core.domain.command.LoginResult;
import com.fightclub.core.inbound.LoginUserUseCase;
import com.fightclub.core.outbound.ReadUserPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LoginService implements LoginUserUseCase {

    private final ReadUserPort readUserPort;
    private final ValidationService validationService;
    private final PasswordHashingService passwordHashingService;
    private final AuthenticationService authenticationService;


    @Override
    public LoginResult result(LoginCommand command) {


        validationService.validateEmail(command.getEmail());
        validationService.validatePassword(command.getPassword());

        String hashPassword = passwordHashingService.hashPassword(command.getPassword());

        User user = readUserPort.readByEmail(command.getEmail());

        authenticationService.authenticate(hashPassword, user);

        return new LoginResult(user);
    }
}
