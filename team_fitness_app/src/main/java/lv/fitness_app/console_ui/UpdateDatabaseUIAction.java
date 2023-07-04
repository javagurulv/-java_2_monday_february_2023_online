package lv.fitness_app.console_ui;

import com.opencsv.exceptions.CsvException;
import lv.fitness_app.core.domain.Exercise;
import lv.fitness_app.core.databaseUpdate.CsvParser;
import lv.fitness_app.core.databaseUpdate.UpdateExerciseDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class UpdateDatabaseUIAction implements UIAction{

    @Autowired
    private UpdateExerciseDB updater;
    @Autowired
    private CsvParser parser;

    @Override
    public void execute() throws IOException, CsvException {
        List<Exercise> list = new ArrayList<>();
        list.addAll(parser.parseToListOfObjects());
        updater.deleteAllFromExerciseDB();
        updater.updateExerciseDB(list);
    }
}
