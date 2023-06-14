package lv.javaguru.java2.servify.console_ui.user;

import lv.javaguru.java2.servify.console_ui.UIAction;
import lv.javaguru.java2.servify.core.dto.RegistrationDTO;
import lv.javaguru.java2.servify.core.dto.responses.AddUserResponse;
import lv.javaguru.java2.servify.core.services.users.RegistrationUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Scanner;

@Component
public class AddUserUIAction implements UIAction {
    @Autowired private RegistrationUserService addUserService;

    @Override
    public void execute() {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter your First Name");
        String firstName = input.nextLine();
        System.out.println("Enter your Last Name");
        String lastName = input.nextLine();
        System.out.println("Enter your e-mail");
        String email = input.nextLine();
        System.out.println("Enter your phone number");
        String phoneNumber = input.nextLine();
        System.out.println("Enter your password");
        String password = input.nextLine();

        RegistrationDTO request = new RegistrationDTO(firstName, lastName, email, phoneNumber, password);
        AddUserResponse response = addUserService.registerUser(request);

        if (response.hasErrors()) {
            response.getErrors().forEach(coreError ->
                    System.out.println("Error: " + coreError.getField() + " " + coreError.getMessage())
            );
        } else {
            System.out.println("New user id was: " + response.getNewUser().getId());
            System.out.println("Congratulations! New user is registered.");
        }
    }

    @Override
    public String getMenuItem() {
        return "Add new User";
    }

}
