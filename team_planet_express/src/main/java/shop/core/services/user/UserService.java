package shop.core.services.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import shop.core.database.jpa.JpaCartRepository;
import shop.core.database.jpa.JpaUserRepository;
import shop.core.domain.Cart;
import shop.core.domain.User;
import shop.core.enums.UserRole;

import java.util.Optional;

@Component
@Transactional
public class UserService {

    @Autowired
    private JpaUserRepository userRepository;
    @Autowired
    private JpaCartRepository cartRepository;

    public User createUser(UserCreationData userCreationData) {
        User createdUser = userRepository
                .save(new User(userCreationData.getName(), userCreationData.getLoginName(), userCreationData.getPassword(), userCreationData.getUserRole()));
        cartRepository.save(new Cart(createdUser));
        return createdUser;
    }

    public Optional<User> findGuestWithOpenCart() {
        return userRepository.findAll().stream()
                .filter(user -> UserRole.GUEST.equals(user.getUserRole()))
                .filter(user -> cartRepository.findOpenCartByUserId(user.getId()).isPresent())
                .findFirst();
    }

}
