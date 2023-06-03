package lv.fitness_app.console_ui;

import lv.fitness_app.core.requests.LoginUserRequest;
import lv.fitness_app.core.responses.LoginUserResponse;
import lv.fitness_app.core.services.LoginUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class LoginUserUIAction implements UIAction {

    @Autowired
    private LoginUserService loginUserService;
    @Autowired
    private  ProgramMenu programMenu;


    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your email: ");
        String email = scanner.nextLine();
        System.out.println("Enter password: ");
        String password = scanner.nextLine();
        LoginUserRequest request = new LoginUserRequest(email, password);
        LoginUserResponse response = loginUserService.execute(request);

        if (response.hasErrors()) {
            response.getErrors().forEach(coreError -> System.out.println("Error: " + coreError.getField() + " " + coreError.getMessage()));
        } else {
            if (response.isUserLogged()) {
                System.out.println("Login Successful!");
                programMenu.runUserMenu();
            } else {
                System.out.println("Id or Password is Incorrect!");
            }
        }
    }

}
