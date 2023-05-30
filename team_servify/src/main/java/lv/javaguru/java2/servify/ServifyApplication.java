package lv.javaguru.java2.servify;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;


import lv.javaguru.java2.servify.web_ui.config.SpringWebConfiguration;

@SpringBootApplication(exclude = HibernateJpaAutoConfiguration.class)
public class ServifyApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(SpringWebConfiguration.class);

    }
}


