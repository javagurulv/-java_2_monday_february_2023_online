package lv.fitness_app.console_ui;

import lv.fitness_app.core.domain.Difficulty;
import lv.fitness_app.core.domain.Type;
import lv.fitness_app.core.requests.Ordering;
import lv.fitness_app.core.requests.Paging;
import lv.fitness_app.core.requests.SearchExercisesRequest;
import lv.fitness_app.core.services.SearchExercisesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

//@Component
public class SearchExercisesUIAction implements UIAction {

    @Autowired
    private SearchExercisesService searchExercisesService;

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter exercise name: ");
        String name = scanner.nextLine();
        System.out.println("Enter exercise muscle group: ");
        String muscleGroup = scanner.nextLine();
        System.out.println("Enter exercise detailed muscle group: ");
        String detailedMuscleGroup = scanner.nextLine();
        System.out.println("Enter exercise other muscle group: ");
        String otherMuscleGroup = scanner.nextLine();
        System.out.println("Enter exercise type: ");
        Type type = Type.valueOf(scanner.nextLine());
        System.out.println("Enter exercise mechanics: ");
        String mechanics = scanner.nextLine();
        System.out.println("Enter exercise equipment: ");
        String equipment = scanner.nextLine();
        System.out.println("Enter exercise difficulty: ");
        Difficulty difficulty = Difficulty.valueOf(scanner.nextLine());
        System.out.println("Enter exercise description: ");
        String description = scanner.nextLine();
        System.out.println("Enter exercise gif: ");
        String gif = scanner.nextLine();


        System.out.println("Enter orderBy (name||muscle group): ");
        String orderBy = scanner.nextLine();
        System.out.println("Enter orderDirection (ASCENDING||DESCENDING): ");
        String orderDirection = scanner.nextLine();
        Ordering ordering = new Ordering(orderBy, orderDirection);

        System.out.println("Enter pageNumber: ");
        Integer pageNumber = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter pageSize: ");
        Integer pageSize = Integer.parseInt(scanner.nextLine());
        Paging paging = new Paging(pageNumber, pageSize);
/*
        SearchExercisesRequest request = new SearchExercisesRequest(name, muscleGroup, detailedMuscleGroup, otherMuscleGroup,
          type, mechanics, equipment, difficulty, description, gif, ordering, paging);
        SearchExercisesResponse response = searchExercisesService.execute(request);

        if (response.hasErrors()) {
            response.getErrors().forEach(coreError -> System.out.println("Error: " + coreError.getField() + " " + coreError.getMessage()));
        } else {
            response.getExercises().forEach(Exercise::toString);
        }
    } */
    }
}