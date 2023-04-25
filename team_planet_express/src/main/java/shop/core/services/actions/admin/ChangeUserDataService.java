package shop.core.services.actions.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import shop.core.database.Database;

@Component
public class ChangeUserDataService {

    @Autowired
    private Database database;

    public void execute() {
        //TODO change user data
    }

}
