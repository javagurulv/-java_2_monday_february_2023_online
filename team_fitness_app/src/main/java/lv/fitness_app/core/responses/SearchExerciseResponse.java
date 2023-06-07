package lv.fitness_app.core.responses;

import lv.fitness_app.core.domain.Exercise;

import java.util.List;

public class SearchExerciseResponse extends CoreResponse {

    private List<Exercise> exercises;

    public SearchExerciseResponse(List<Exercise> exercises, List<CoreError> errors) {
        super(errors);
        this.exercises = exercises;}


    public List<Exercise> getExercises() {return exercises;}
}
