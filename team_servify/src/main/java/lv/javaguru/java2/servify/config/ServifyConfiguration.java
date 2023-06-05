package lv.javaguru.java2.servify.config;

import lv.javaguru.java2.servify.core.database.jpa.JpaUserRepository;
import lv.javaguru.java2.servify.core.database.jpa.JpaUserTypeRepository;
import lv.javaguru.java2.servify.core.domain.UserEntity;
import lv.javaguru.java2.servify.core.domain.UserType;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.HashSet;
import java.util.Set;

@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = "lv.javaguru.java2.servify")
@PropertySource(value = "classpath:application.properties")
@EnableTransactionManagement
@EntityScan(basePackages = "lv.javaguru.java2.servify.core.domain")
@EnableJpaRepositories(value = "lv.javaguru.java2.servify.core.database.jpa")
public class ServifyConfiguration {

}
