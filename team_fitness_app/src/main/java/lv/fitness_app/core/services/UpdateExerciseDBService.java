package lv.fitness_app.core.services;

import com.opencsv.exceptions.CsvException;
import lv.fitness_app.core.database.databaseUpdate.CsvParser;
import lv.fitness_app.core.database.databaseUpdate.GetExercisesFromWebsite;
import lv.fitness_app.core.database.databaseUpdate.UpdateExerciseDB;
import lv.fitness_app.core.domain.Exercise;
import lv.fitness_app.core.requests.UpdateExerciseDBRequest;
import lv.fitness_app.core.responses.UpdateExerciseDBResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Component
@Transactional
public class UpdateExerciseDBService {

    @Autowired
    private UpdateExerciseDB updater;
    @Autowired
    private CsvParser parser;
    @Autowired
    private GetExercisesFromWebsite getExercisesFromWebsite;

    public UpdateExerciseDBResponse execute(UpdateExerciseDBRequest request) throws IOException, CsvException {
        getExercisesFromWebsite.execute();
        List<Exercise> updatingResult = new ArrayList<>();
        updatingResult.addAll(parser.parseToListOfObjects());
        updater.updateExerciseDB(updatingResult);
        String message = "Database successfully updated!";
        return new UpdateExerciseDBResponse(message);
    }
}
