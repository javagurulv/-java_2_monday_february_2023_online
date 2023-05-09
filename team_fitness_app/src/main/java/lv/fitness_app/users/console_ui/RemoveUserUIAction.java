package lv.fitness_app.users.console_ui;

import lv.fitness_app.users.core.requests.RemoveUserRequest;
import lv.fitness_app.users.core.responses.RemoveUserResponse;
import lv.fitness_app.users.core.services.RemoveUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class RemoveUserUIAction implements UIAction {

    @Autowired private RemoveUserService removeUserService;


    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your email: ");
        String email = scanner.nextLine();
        System.out.println("Enter password: ");
        String password = scanner.nextLine();
        RemoveUserRequest request = new RemoveUserRequest(email, password);
        RemoveUserResponse response = removeUserService.execute(request);

        if (response.hasErrors()) {
            response.getErrors().forEach(coreError -> System.out.println("Error: " + coreError.getField() + " " + coreError.getMessage()));
        } else {
            if (response.isUserRemoved()) {
                System.out.println("User removed successfully");
            } else {
                System.out.println("User was not removed");
            }
        }
    }
}
