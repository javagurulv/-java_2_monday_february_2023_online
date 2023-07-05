package shop.core.services.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import shop.core.database.CartRepository;
import shop.core.database.UserRepository;
import shop.core.domain.user.User;
import shop.core.domain.user.UserRole;

import java.util.Optional;

import static shop.core.database.specifications.CartSpecs.findOpenCartForUser;

@Component
@Transactional
@Deprecated
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CartRepository cartRepository;

    public User createUser() {
        return new User();
    }

    public Optional<User> findGuestWithOpenCart() {
        return userRepository.findAll().stream()
                .filter(user -> UserRole.GUEST.equals(user.getUserRole()))
                .filter(user -> cartRepository.findOne(findOpenCartForUser(user)).isPresent())
                .findFirst();
    }

}
