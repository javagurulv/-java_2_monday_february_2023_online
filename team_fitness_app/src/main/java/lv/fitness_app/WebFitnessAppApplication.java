package lv.fitness_app;

import lv.fitness_app.web_ui.config.SpringWebConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

@SpringBootApplication(exclude = HibernateJpaAutoConfiguration.class)
public class WebFitnessAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringWebConfiguration.class);
    }

}