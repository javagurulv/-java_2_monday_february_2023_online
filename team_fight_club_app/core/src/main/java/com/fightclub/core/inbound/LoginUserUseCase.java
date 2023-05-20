package com.fightclub.core.inbound;

import com.fightclub.core.domain.command.LoginCommand;
import com.fightclub.core.domain.command.LoginResult;

public interface LoginUserUseCase {

    LoginResult result(LoginCommand command);
}
