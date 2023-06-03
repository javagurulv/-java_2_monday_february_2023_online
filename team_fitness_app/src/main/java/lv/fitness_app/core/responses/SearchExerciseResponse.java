package lv.fitness_app.core.responses;

import lv.fitness_app.core.domain.Exercise;

import java.util.List;

public class SearchExerciseResponse {

    private List<Exercise> exercises;

    public SearchExerciseResponse(List<Exercise> exercises, List<CoreError> errors) {
        super();
        this.exercises = exercises;}


    public List<Exercise> getExercises() {return exercises;}
}
