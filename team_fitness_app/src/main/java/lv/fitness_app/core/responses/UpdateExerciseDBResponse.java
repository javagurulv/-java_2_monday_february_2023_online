package lv.fitness_app.core.responses;

import lv.fitness_app.core.domain.Exercise;
import lv.fitness_app.core.domain.User;

import java.util.List;

public class UpdateExerciseDBResponse extends CoreResponse {


    private String result;

    public UpdateExerciseDBResponse(String result) {
        this.result = result;
    }

    public String getResult() {
        return result;

    }
}
