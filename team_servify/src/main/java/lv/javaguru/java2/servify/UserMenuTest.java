package lv.javaguru.java2.servify;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import lv.javaguru.java2.servify.config.DetailListConfiguration;
import lv.javaguru.java2.servify.console_ui.user.AddUserUIAction;
import lv.javaguru.java2.servify.console_ui.ExitUIAction;
import lv.javaguru.java2.servify.console_ui.user.GetAllUsersUIAction;
import lv.javaguru.java2.servify.console_ui.user.SetUserNotActiveUIAction;
import lv.javaguru.java2.servify.core.database.UsersDatabase;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class UserMenuTest {

    @Autowired private UsersDatabase usersDatabase;

    public void execute() {

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(DetailListConfiguration.class);

        while (true) {
            printAdminMenu();
            int userChoice = getUserChoice();
            switch (userChoice) {
                case 1 -> {
                    AddUserUIAction uiAction = applicationContext.getBean(AddUserUIAction.class);
                    uiAction.execute();
                }
                case 2 -> {
                    SetUserNotActiveUIAction uiAction = applicationContext.getBean(SetUserNotActiveUIAction.class);
                    uiAction.execute();
                }
                case 3 -> {
                    GetAllUsersUIAction uiAction = applicationContext.getBean(GetAllUsersUIAction.class);
                    uiAction.execute();
                }
                case 4 -> {
                    ExitUIAction uiAction = applicationContext.getBean(ExitUIAction.class);
                    uiAction.execute();
                }
            }
        }
    }

    private static int getUserChoice() {
        System.out.println("Enter menu item number to execute:");
        Scanner scanner = new Scanner(System.in);
        return Integer.parseInt(scanner.nextLine());
    }

    private static void printAdminMenu() {
        System.out.println("User menu:");
        System.out.println("1. Add user to list");
        System.out.println("2. Delete user from list");
        System.out.println("3. Show all users in the list");
        System.out.println("4. Exit");
        System.out.println();
    }
}
