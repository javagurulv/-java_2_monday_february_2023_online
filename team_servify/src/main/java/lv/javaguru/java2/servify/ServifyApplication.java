package lv.javaguru.java2.servify;

import lv.javaguru.java2.servify.core.database.jpa.JpaUserRepository;
import lv.javaguru.java2.servify.core.database.jpa.JpaUserTypeRepository;
import lv.javaguru.java2.servify.core.domain.UserEntity;
import lv.javaguru.java2.servify.core.domain.UserType;
import lv.javaguru.java2.servify.core.services.Test;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import lv.javaguru.java2.servify.web_ui.config.SpringWebConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class ServifyApplication {
    //private Test test;
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(SpringWebConfiguration.class);
        //new Test().runInit();
    }

//    @Bean
//    CommandLineRunner run(JpaUserTypeRepository roleRepository, JpaUserRepository userRepository, PasswordEncoder encoder) {
//        return args -> {
//            if (roleRepository.findByAuthority("ADMIN").isPresent()) return;
//            UserType someUser = new UserType("ADMIN");
//            //roleRepository.save(someUser);
//            //UserType adminRole = roleRepository.save(new UserType("ADMIN"));
//            //roleRepository.save(new UserType("USER"));
//            Set<UserType> roles = new HashSet<>();
//            //roles.add(adminRole);
//            //UserEntity admin = new UserEntity("admin@gmail.com", encoder.encode("7777"), roles);
//
//            //userRepository.save(admin);
//        };
//    }

}


