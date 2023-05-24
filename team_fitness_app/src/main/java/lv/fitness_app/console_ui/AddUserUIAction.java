package lv.fitness_app.console_ui;

import lv.fitness_app.core.requests.AddUserRequest;
import lv.fitness_app.core.responses.AddUserResponse;
import lv.fitness_app.core.services.AddUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class AddUserUIAction implements UIAction {

    @Autowired private AddUserService addUserService;


    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your email: ");
        String email = scanner.nextLine();
        System.out.println("Enter username: ");
        String username = scanner.nextLine();
        System.out.println("Enter password: ");
        String password = scanner.nextLine();
        AddUserRequest request = new AddUserRequest(email, username, password);
        AddUserResponse response = addUserService.execute(request);

        if (response.hasErrors()) {
            response.getErrors().forEach(coreError ->
                    System.out.println("Error: " + coreError.getField() + " " + coreError.getMessage())
            );
        } else {
            System.out.println("You are successfully added!");
        }
    }

}
