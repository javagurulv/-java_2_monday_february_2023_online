package lv.javaguru.java2.servify.console_ui.user;

import lv.javaguru.java2.servify.console_ui.UIAction;
import lv.javaguru.java2.servify.core.services.users.GetAllUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GetAllUserUIAction implements UIAction {
    @Autowired
    private GetAllUsersService getAllUsersService;

    @Override
    public void execute() {
//        System.out.println("Users list");
//        getAllUsersService.getAll();
    }

    @Override
    public String getMenuItem() {
        return "Get all users";
    }
}
