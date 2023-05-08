package lv.fitness_app.users.console_ui;

import lv.fitness_app.users.core.requests.GetAllUsersRequest;
import lv.fitness_app.users.core.responses.GetAllUsersResponse;
import lv.fitness_app.users.core.services.GetAllUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GetAllUsersUIAction implements UIAction {

    @Autowired private GetAllUsersService getUsersService;

    @Override
    public void execute() {
        System.out.println("User list: ");
        GetAllUsersRequest request = new GetAllUsersRequest();
        GetAllUsersResponse response = getUsersService.execute(request);
        response.getUsers().forEach(System.out::println);
        System.out.println("User list end.");
    }

}
