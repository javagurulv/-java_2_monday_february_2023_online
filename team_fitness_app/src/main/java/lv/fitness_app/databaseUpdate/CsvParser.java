package lv.fitness_app.databaseUpdate;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;
import lv.fitness_app.core.domain.Exercise;
import one.util.streamex.StreamEx;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CsvParser {

    public List<Exercise> parseToListOfObjects() throws IOException, CsvException {
        List<Exercise> list = new ArrayList<>();
        CSVParser parser = new CSVParserBuilder()
                .withSeparator(';')
                .build();
        try (CSVReader reader = new CSVReaderBuilder(new FileReader("team_fitness_app/files/exercises.csv"))
                .withCSVParser(parser)
                .build()) {
            List<String[]> r = reader.readAll();
            for (String[] row : r) {
                Exercise exercise = new Exercise(row[0], row[1], row[2], row[3], row[4], row[5], row[6], row[7], row[8], row[9]);
                list.add(exercise);
            }
            list.remove(0);
        } catch (CsvException e) {
            throw new RuntimeException(e);
        } return StreamEx.of(list)
                .distinct(Exercise::getName)
                .toList();
    }
}
