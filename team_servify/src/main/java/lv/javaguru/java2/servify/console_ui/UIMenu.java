package lv.javaguru.java2.servify.console_ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Scanner;

@Component
public class UIMenu {
    private final List<UIAction> uiActions;
    @Autowired
    public UIMenu(List<UIAction> uiActions) {
        this.uiActions = uiActions;
    }

    public void startUI() {
        showWelcomeMessage();
        while (true) {
            try {
                printMenu(uiActions);
                var userChoice = userChoiceFromMenu();
                uiActions.get(userChoice).execute();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static void printMenu(List<UIAction> uiActions) {
        for (int i = 0; i < uiActions.size(); i++) {
            System.out.println(i + 1 + " " + uiActions.get(i).getMenuItem());
        }
    }

    private static void showWelcomeMessage() {
        System.out.println("|-----------------------------------------------------------------|");
        System.out.println("| Welcome to the SERVIFY APP - calculate price for your paint job |");
        System.out.println("|-----------------------------------------------------------------|");
        System.out.println();
    }

    private static void showErrorMessage() {
        System.out.println("Wrong input, try again, please use only 1 .. 8 for main menu selection.");
        System.out.println();
    }

    private static int userChoiceFromMenu() {
        return new Scanner(System.in).nextInt() - 1;
    }
}
