package lv.javaguru.java2.servify.core.services.users;

import lv.javaguru.java2.servify.core.database.jpa.JpaUserRepository;
import lv.javaguru.java2.servify.core.domain.UserEntity;
import lv.javaguru.java2.servify.core.domain.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private JpaUserRepository userRepository;
    @Autowired
    private PasswordEncoder encoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("In the detail service");
//        if (!username.equals("Alex")) throw new UsernameNotFoundException("Not Alex");
//
//        Set<UserType> roles = new HashSet<>();
//        roles.add(new UserType(2L, "CUSTOMER"));
//
//        return new UserEntity("Alex",encoder.encode("7777"), roles);
        return userRepository.findByUserName(username).orElseThrow(()-> new UsernameNotFoundException("user is not valid"));
    }
}
