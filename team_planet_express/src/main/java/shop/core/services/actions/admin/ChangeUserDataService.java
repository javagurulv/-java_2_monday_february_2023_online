package shop.core.services.actions.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import shop.core.database.UserRepository;

@Component
@Transactional
public class ChangeUserDataService {

    @Autowired
    private UserRepository userRepository;

    public void execute() {
        //TODO change user data
    }

}
