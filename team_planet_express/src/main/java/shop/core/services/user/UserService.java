package shop.core.services.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import shop.core.database.CartRepository;
import shop.core.database.UserRepository;
import shop.core.domain.cart.Cart;
import shop.core.domain.user.User;
import shop.core.domain.user.UserRole;

import java.util.Optional;

@Component
@Transactional
@Deprecated
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CartRepository cartRepository;

    public User createUser(UserCreationData userCreationData) {
        User createdUser = userRepository
                .save(new User(userCreationData.getName(), userCreationData.getLoginName(), userCreationData.getPassword(), userCreationData.getUserRole()));
        cartRepository.save(new Cart(createdUser));
        return createdUser;
    }

    public Optional<User> findGuestWithOpenCart() {
        return userRepository.getAllUsers().stream()
                .filter(user -> UserRole.GUEST.equals(user.getUserRole()))
                .filter(user -> cartRepository.findOpenCartForUserId(user.getId()).isPresent())
                .findFirst();
    }

}
