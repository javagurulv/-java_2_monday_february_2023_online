package lv.javaguru.java2.servify.console_ui;

import lv.javaguru.java2.servify.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

@Component
public class UIMenu {
    private final List<UIAction> uiActions;
    private UserEntity currentUser;
    @Autowired
    public UIMenu(List<UIAction> uiActions) {
        this.uiActions = uiActions;
        this.currentUser = new UserEntity();
    }

    public void startUI() {
        showWelcomeMessage();
        while (true) {
            try {
                //printMenu(uiActions);
                printMenu(filteredMenuByUserType(currentUser));
                var userChoice = userChoiceFromMenu();
                if (userChoice < 0 || userChoice > uiActions.size() - 1) {
                    showErrorMessage();
                } else {
                    uiActions.get(userChoice).execute();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public List<UIAction> filteredMenuByUserType(UserEntity currentUser) {
        return uiActions.stream()
                .filter(uiAction -> uiAction.getAccessUserByType().contains(currentUser.getUserType()))
                .collect(Collectors.toList());
    }
    public void showErrorMessage() {
        System.out.println("Wrong input, try again, please use only 1 .. " + uiActions.size() + " for main menu selection.");
        System.out.println();
    }

    private static void printMenu(List<UIAction> uiActions) {
//        uiActions.stream()
//                .map(UIAction::getMenuItem)
//                .forEach(System.out::println); //TODO how concatenate in stream
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

    private static int userChoiceFromMenu() {
        return new Scanner(System.in).nextInt() - 1;
    }
}
