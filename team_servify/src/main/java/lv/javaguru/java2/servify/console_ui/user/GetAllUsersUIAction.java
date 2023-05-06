package lv.javaguru.java2.servify.console_ui.user;

import lv.javaguru.java2.servify.console_ui.UIAction;
import lv.javaguru.java2.servify.core.responses.user.GetAllUsersResponse;
import lv.javaguru.java2.servify.core.services.user.GetAllUsersService;
import lv.javaguru.java2.servify.domain.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GetAllUsersUIAction implements UIAction {
    @Autowired private GetAllUsersService getAllUsersService;

    @Override
    public void execute() {
        System.out.println("User list: ");
        GetAllUsersResponse response = getAllUsersService.execute();
        response.getUsers().forEach(System.out::println);
        System.out.println("User list end.");
    }

    @Override
    public String getMenuItem() {
        return "Get all users";
    }

    @Override
    public List<UserType> getAccessUserByType() {
        return new ArrayList<>(List.of(
                UserType.MANAGER
        ));
    }
}
