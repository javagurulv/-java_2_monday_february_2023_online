package lv.fitness_app.console_ui;

import lv.fitness_app.core.requests.GetAllUsersRequest;
import lv.fitness_app.core.responses.GetAllUsersResponse;
import lv.fitness_app.core.services.GetAllUsersService;
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
