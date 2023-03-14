package console_ui;

import eln.database.Database;

import java.util.Scanner;

public class ValidateUserUIAction implements UIAction{

    private Database database;

    public ValidateUserUIAction(Database database) {
        this.database = database;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter user ID");
        Long id = Long.parseLong(scanner.nextLine());
        System.out.println("Enter password: ");
        String password = scanner.nextLine();
        if (database.login(id, password))
            System.out.println("Login Successful!");
        else
            System.out.println("Id or Password is Incorrect!");
    }
}
