package java2.eln.console_ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

@Component
public class ProgramMenu {

    private Map<Integer, UIAction> menuNumberToUIActionMap;

    @Autowired
    public ProgramMenu(List<UIAction> uiActions) {
        menuNumberToUIActionMap = new HashMap<>();
        menuNumberToUIActionMap.put(1, findUIAction(uiActions, AddReactionUIAction.class));
        menuNumberToUIActionMap.put(2, findUIAction(uiActions, DelReactionByCodeUIAction.class));
        menuNumberToUIActionMap.put(21, findUIAction(uiActions, DelReactionByIdUIAction.class));
        menuNumberToUIActionMap.put(3, findUIAction(uiActions, GetAllReactionUIAction.class));
        menuNumberToUIActionMap.put(4, findUIAction(uiActions, FindReactionByMainProductUIAction.class));
        menuNumberToUIActionMap.put(5, findUIAction(uiActions, FindReactionUIAction.class));
        menuNumberToUIActionMap.put(6, findUIAction(uiActions, ExitUIAction.class));

    }

    private UIAction findUIAction(List<UIAction> uiActions, Class uiActionClass) {
        return uiActions.stream()
                .filter(uiAction -> uiAction.getClass().equals(uiActionClass))
                .findFirst()
                .get();
    }

    public void printProgramMenu() {
        System.out.println();
        System.out.println("Program menu:");
        System.out.println("1. Add Reaction to list");
        System.out.println("2. Delete Reaction from list by code");
        System.out.println("21. Delete Reaction from list by ID");
        System.out.println("3. Show all Reactions in the list");
        System.out.println("4. Find reactions by main product");
        System.out.println("5. Find reactions");
        System.out.println("6. Exit");
        System.out.println();
    }

    public int getMenuNumberFromUser() {
        System.out.println("Enter menu item number to execute:");
        Scanner scanner = new Scanner(System.in);
        return Integer.parseInt(scanner.nextLine());
    }

    public void executeSelectedMenuItem(int selectedMenu) {
        menuNumberToUIActionMap.get(selectedMenu).execute();
    }
}
