package users;

import users.console_ui.*;
import users.database.Database;
import users.database.InMemoryDatabaseImpl;
import users.services.AddUserService;
import users.services.RemoveUserService;
import users.services.ValidateUserService;

import java.util.Scanner;

public class UserListApplication {

    private static Database database = new InMemoryDatabaseImpl();
    private static AddUserService addUserService = new AddUserService(database);
    private static UIAction addUserUIAction = new AddUserUIAction(addUserService);
    private static RemoveUserService removeUserService = new RemoveUserService(database);
    private static UIAction removeUserUIAction = new RemoveUserUIAction(removeUserService);
    private static ValidateUserService validateUserService = new ValidateUserService(database);
    private static UIAction validateUserUIAction = new ValidateUserUIAction(validateUserService);
    private static UIAction exitUIAction = new ExitUIAction();

    public static void main(String[] args) {

        while (true) {
            printProgramMenu();
            int menuNumber = getMenuNumberFromUser();
            executeSelectedMenuItem(menuNumber);
        }
    }

    private static void printProgramMenu() {
        System.out.println();
        System.out.println("Program menu:");
        System.out.println("1. Register new user.");
        System.out.println("2. Login.");
        System.out.println("3. Delete user from eln.database.");
        System.out.println("4. Exit");
        System.out.println();
    }

    private static int getMenuNumberFromUser() {
        System.out.println("Enter menu item number to execute:");
        Scanner scanner = new Scanner(System.in);
        return Integer.parseInt(scanner.nextLine());
    }

    private static void executeSelectedMenuItem(int selectedMenu) {

        switch (selectedMenu) {
            case 1 -> addUserUIAction.execute();
            case 2 -> validateUserUIAction.execute();
            case 3 -> removeUserUIAction.execute();
            case 4 -> exitUIAction.execute();
        }
    }
}