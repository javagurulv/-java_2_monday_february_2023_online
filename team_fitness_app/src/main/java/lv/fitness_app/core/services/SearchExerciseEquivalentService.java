package lv.fitness_app.core.services;

import lv.fitness_app.core.database.jpa.JpaExerciseRepository;
import lv.fitness_app.core.domain.Exercise;
import lv.fitness_app.core.requests.Ordering;
import lv.fitness_app.core.requests.Paging;
import lv.fitness_app.core.requests.SearchExerciseRequest;
import lv.fitness_app.core.responses.CoreError;
import lv.fitness_app.core.responses.SearchExerciseResponse;
import lv.fitness_app.core.services.validators.SearchExerciseEquivalentRequestValidator;
import lv.fitness_app.core.services.validators.SearchExerciseRequestValidator;
import lv.fitness_app.core.database.ExerciseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

@Component
@Transactional
public class SearchExerciseEquivalentService {

    @Autowired
    private JpaExerciseRepository exerciseRepository;
    @Autowired
    private SearchExerciseEquivalentRequestValidator validator;

    public SearchExerciseResponse execute(SearchExerciseRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new SearchExerciseResponse(null, errors);
        }
        List<Exercise> exercises = search(request);
//        exercises = order(exercises, request.getOrdering());
//        exercises = paging(exercises, request.getPaging());

        return new SearchExerciseResponse(exercises, null);
    }

    private List<Exercise> search(SearchExerciseRequest request) {
        List<Exercise> exercises = new ArrayList<>();
        if (request.isNameProvided()) {
            exercises = exerciseRepository.findByName(request.getName());

        }
        if (exercises.size() == 0) {
            System.out.println("No exercises with this name found. ");

        } else {
            for (int i = 0; i < exercises.size(); i++) {
                System.out.println(i + 1 + " . " + exercises.get(i));
            }
        }
        System.out.println("Choose one exercise from the list above, input number.");
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        String activeString = exercises.get(n - 1).getMuscleGroup();
        exercises = exerciseRepository.findByMuscleGroup(activeString);

        return exercises;
    }


//    private List<Exercise> order(List<Exercise> exercises, Ordering ordering) {
//        if (orderingEnabled && (ordering != null)) {
//            Comparator<Exercise> comparator = ordering.getOrderBy().equals("name")
//                    ? Comparator.comparing(Exercise::getName)
//                    : Comparator.comparing(Exercise::getMuscleGroup);
//            if (ordering.getOrderDirection().equals("DESCENDING")) {
//                comparator = comparator.reversed();
//            }
//            return exercises.stream().sorted(comparator).collect(Collectors.toList());
//        } else {
//            return exercises;
//        }
//    }
//
//    private List<Exercise> paging(List<Exercise> exercises, Paging paging) {
//        if (pagingEnabled && (paging != null)) {
//            int skip = (paging.getPageNumber() - 1) * paging.getPageSize();
//            return exercises.stream()
//                    .skip(skip)
//                    .limit(paging.getPageSize())
//                    .collect(Collectors.toList());
//        } else {
//            return exercises;
//        }
//    }
}
