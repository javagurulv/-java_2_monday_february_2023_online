package lv.fitness_app.core.responses;

import lv.fitness_app.core.domain.Exercise;
import lv.fitness_app.core.domain.User;

import java.util.List;

public class UpdateExerciseDBResponse extends CoreResponse {


    List<Exercise> updatingResult;

    public UpdateExerciseDBResponse(List<Exercise> updatingResult) {
        this.updatingResult = updatingResult;
    }

    public List<Exercise> getUpdatingResult() {
        System.out.println("Success");
        return updatingResult;
    }
    }
