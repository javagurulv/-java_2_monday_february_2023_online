package shop.core.services.actions.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import shop.core.database.Repository;

@Component
public class ChangeUserDataService {

    @Autowired
    private Repository repository;

    public void execute() {
        //TODO change user data
    }

}
