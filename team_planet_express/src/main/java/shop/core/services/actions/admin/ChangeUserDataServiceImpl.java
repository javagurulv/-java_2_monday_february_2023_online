package shop.core.services.actions.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.core.database.UserRepository;
import shop.core_api.entry_point.admin.ChangeUserDataService;

@Service
@Transactional
public class ChangeUserDataServiceImpl implements ChangeUserDataService {

    @Autowired
    private UserRepository userRepository;

    public void execute() {
        //TODO change user data
    }

}
