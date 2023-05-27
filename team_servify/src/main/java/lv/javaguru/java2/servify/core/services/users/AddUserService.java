package lv.javaguru.java2.servify.core.services.users;

import lv.javaguru.java2.servify.core.database.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddUserService {
    @Autowired
    private UserRepository userRepository;


}
