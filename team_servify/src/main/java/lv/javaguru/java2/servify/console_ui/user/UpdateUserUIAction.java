package lv.javaguru.java2.servify.console_ui.user;

import lv.javaguru.java2.servify.console_ui.UIAction;
import lv.javaguru.java2.servify.core.services.user.UpdateUserService;
import lv.javaguru.java2.servify.domain.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
public class UpdateUserUIAction implements UIAction {
    @Autowired
    private UpdateUserService updateUserService;
    @Override
    public void execute() {
        System.out.println("TODO update user UI"); //TODO write UI for Update User
    }

    @Override
    public String getMenuItem() {
        return "UpdateUser";
    }

    @Override
    public List<UserType> getAccessUserByType() {
        return new ArrayList<>(List.of(
                UserType.CUSTOMER,
                UserType.MANAGER
        ));
    }
}
