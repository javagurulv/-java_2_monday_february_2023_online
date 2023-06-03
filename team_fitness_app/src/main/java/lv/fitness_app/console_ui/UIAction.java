package lv.fitness_app.console_ui;

import com.opencsv.exceptions.CsvException;

import java.io.IOException;

public interface UIAction {

    void execute() throws IOException, CsvException;
}
