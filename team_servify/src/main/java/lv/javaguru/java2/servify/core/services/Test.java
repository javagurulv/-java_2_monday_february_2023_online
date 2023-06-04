package lv.javaguru.java2.servify.core.services;

import lv.javaguru.java2.servify.core.database.jpa.JpaUserRepository;
import lv.javaguru.java2.servify.core.database.jpa.JpaUserTypeRepository;
import lv.javaguru.java2.servify.core.domain.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class Test {
    @Autowired private JpaUserTypeRepository roleRepo;
    @Autowired private JpaUserRepository userRepo;

    public void runInit() {
        UserType someRole = new UserType("ADMIN");
        roleRepo.save(someRole);
        //UserType adminRole = roleRepository.save(new UserType("ADMIN"));
        //roleRepo.save(new UserType("USER"));
        Set<UserType> roles = new HashSet<>();
        //roles.add(adminRole);
        //UserEntity admin = new UserEntity("admin@gmail.com", encoder.encode("7777"), roles);

        //userRepository.save(admin);
    }
}
