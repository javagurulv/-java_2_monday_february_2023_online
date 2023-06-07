package shop.core.services.actions.shared;

import com.vaadin.flow.spring.security.AuthenticationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import shop.core.database.UserRepository;
import shop.core.domain.user.User;
import shop.core_api.entry_point.shared.SecurityService;

import java.util.Optional;

@Service
public class SecurityServiceImpl implements SecurityService {

    private final AuthenticationContext authenticationContext;
    @Autowired
    private UserRepository userRepository;

    public SecurityServiceImpl(AuthenticationContext authenticationContext) {
        this.authenticationContext = authenticationContext;
    }

    public Optional<UserDetails> getAuthenticatedUser() {
        return authenticationContext.getAuthenticatedUser(UserDetails.class);
    }

    public void logout() {
        authenticationContext.logout();
    }


    public Optional<User> getAuthenticatedUserFromDB() {

        Optional<User> user;
        Optional<UserDetails> authenticatedUser = authenticationContext.getAuthenticatedUser(UserDetails.class);
        if (authenticatedUser.isPresent()) {
            user = userRepository.findByLoginName(authenticatedUser.get().getUsername());
        } else
            user = Optional.empty();
        return user;
    }
}