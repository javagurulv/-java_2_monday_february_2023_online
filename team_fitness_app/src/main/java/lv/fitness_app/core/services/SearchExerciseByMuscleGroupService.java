package lv.fitness_app.core.services;

import lv.fitness_app.core.domain.Exercise;
import lv.fitness_app.core.requests.Ordering;
import lv.fitness_app.core.requests.Paging;
import lv.fitness_app.core.requests.SearchExerciseByMuscleGroupRequest;
import lv.fitness_app.core.responses.CoreError;
import lv.fitness_app.core.responses.SearchExerciseResponse;
import lv.fitness_app.database.ExerciseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Transactional
public class SearchExerciseByMuscleGroupService {

    @Value("${search.ordering.enabled}")
    private boolean orderingEnabled;

    @Value("${search.paging.enabled}")
    private boolean pagingEnabled;

    @Autowired
    private ExerciseRepository exerciseRepository;
    @Autowired private SearchExerciseByMuscleGroupRequestValidator validator;

    public SearchExerciseResponse execute(SearchExerciseByMuscleGroupRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new SearchExerciseResponse(null, errors);
        }

        List<Exercise> exercises = search(request);
    exercises = order(exercises, request.getOrdering());
    exercises = paging(exercises, request.getPaging());

        return new SearchExerciseResponse(exercises, null);
    }

    private List<Exercise> search(SearchExerciseByMuscleGroupRequest request) {
        List<Exercise> exercises = new ArrayList<>();
        if (request.isMuscleGroupProvided()) {
            exercises = exerciseRepository.findByMuscleGroup(request.getMuscleGroup());
        }
        return exercises;
    }

     private List<Exercise> order(List<Exercise> exercises, Ordering ordering) {
        if (orderingEnabled && (ordering != null)) {
            Comparator<Exercise> comparator = ordering.getOrderBy().equals("muscle group")
                    ? Comparator.comparing(Exercise::getName)
                    : Comparator.comparing(Exercise::getMuscleGroup);
            if (ordering.getOrderDirection().equals("DESCENDING")) {
                comparator = comparator.reversed();
            }
            return exercises.stream().sorted(comparator).collect(Collectors.toList());
        } else {
            return exercises;
        }
    }

    private List<Exercise> paging(List<Exercise> exercises, Paging paging) {
        if (pagingEnabled && (paging != null)) {
            int skip = (paging.getPageNumber() - 1) * paging.getPageSize();
            return exercises.stream()
                    .skip(skip)
                    .limit(paging.getPageSize())
                    .collect(Collectors.toList());
        } else {
            return exercises;
        }
    }

}
