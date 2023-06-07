package lv.fitness_app;


import com.opencsv.exceptions.CsvException;
import lv.fitness_app.web_ui.config.SpringWebConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.ApplicationContext;
import lv.fitness_app.config.SpringCoreConfiguration;
import lv.fitness_app.console_ui.ProgramMenu;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;

@SpringBootApplication(exclude = HibernateJpaAutoConfiguration.class)
public class WebFitnessAppApplication {

    public static void main(String[] args) throws IOException, CsvException {
        ConfigurableApplicationContext context = SpringApplication.run(SpringWebConfiguration.class);

//        ProgramMenu programMenu = context.getBean(ProgramMenu.class);
//        while (true) {
//            programMenu.print();
//            int menuNumber = programMenu.getMenuNumberFromUser();
//            programMenu.executeSelectedMenuItem(menuNumber);
//        }
    }


}