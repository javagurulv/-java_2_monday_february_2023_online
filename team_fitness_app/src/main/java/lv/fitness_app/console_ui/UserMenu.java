package lv.fitness_app.console_ui;

import com.opencsv.exceptions.CsvException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

@Component
public class UserMenu {

    private Map<Integer, UIAction> menuNumberToUIAction;

    @Autowired
    public UserMenu(List<UIAction> uiActions) {
        menuNumberToUIAction = new HashMap<>();
//        menuNumberToUIAction.put(1, findeUIAction(uiActions, SearchExerciseByNameUIAction.class));
//        menuNumberToUIAction.put(2, findeUIAction(uiActions, SearchExerciseByMuscleGroupUIAction.class));
//        menuNumberToUIAction.put(3, findeUIAction(uiActions, SearchEquivalentByNameUIAction.class));
        menuNumberToUIAction.put(4, findeUIAction(uiActions, UpdateDatabaseUIAction.class));
        menuNumberToUIAction.put(5, findeUIAction(uiActions, ExitUIAction.class));

    }

    private UIAction findeUIAction(List<UIAction> uiActions, Class uiActionClass) {
        return uiActions.stream()
                .filter(uiAction -> uiAction.getClass().equals(uiActionClass))
                .findFirst()
                .get();
    }

    public void printUserMenu() {
        System.out.println();
        System.out.println("Choose one of the following");
        System.out.println("1. Search exercise by name");
        System.out.println("2. Search by muscle group name");
        System.out.println("3. Search equivalent exercise by name");
        System.out.println("4. Update exercise database");
        System.out.println("5. Exit");
    }

    public void executeSelectedMenuItem(int SelectedMenu) throws IOException, CsvException {
        menuNumberToUIAction.get(SelectedMenu).execute();
    }


}
