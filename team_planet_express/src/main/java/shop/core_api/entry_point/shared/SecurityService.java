package shop.core_api.entry_point.shared;

import org.springframework.security.core.userdetails.UserDetails;
import shop.core.domain.user.User;

import java.util.Optional;


public interface SecurityService {

    public Optional<UserDetails> getAuthenticatedUser();

    public void logout();


    public Optional<User> getAuthenticatedUserFromDB();
}