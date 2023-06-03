package lv.fitness_app;


import com.opencsv.exceptions.CsvException;
import org.springframework.context.ApplicationContext;
import lv.fitness_app.config.UserListConfiguration;
import lv.fitness_app.console_ui.ProgramMenu;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;


public class FitnessAppApplication {

    public static void main(String[] args) throws IOException, CsvException {
        ApplicationContext applicationContext = createApplicationContext();
        ProgramMenu programMenu = applicationContext.getBean(ProgramMenu.class);
        while (true) {
            programMenu.print();
            int menuNumber = programMenu.getMenuNumberFromUser();
            programMenu.executeSelectedMenuItem(menuNumber);
        }
    }

    private static ApplicationContext createApplicationContext() {
        return new AnnotationConfigApplicationContext(UserListConfiguration.class);
    }
}