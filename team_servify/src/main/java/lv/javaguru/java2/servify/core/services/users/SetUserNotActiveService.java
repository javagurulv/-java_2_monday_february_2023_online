package lv.javaguru.java2.servify.core.services.users;


import lv.javaguru.java2.servify.core.database.UserRepository;
import lv.javaguru.java2.servify.core.domain.UserEntity;
import lv.javaguru.java2.servify.core.dto.requests.SetUserNotActiveRequest;
import lv.javaguru.java2.servify.core.dto.responses.CoreError;
import lv.javaguru.java2.servify.core.dto.responses.SetUserNotActiveResponse;
import lv.javaguru.java2.servify.core.services.validators.SetUserNotActiveValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class SetUserNotActiveService {
    @Autowired private UserRepository userDB;
    @Autowired private SetUserNotActiveValidator validator;

    public SetUserNotActiveResponse execute(SetUserNotActiveRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new SetUserNotActiveResponse(errors);
        }
        userDB.findById(request.getUserIdToSetInactive())
                .map(user -> updateFields(user, request))
                .ifPresent(userDB::update);
        return new SetUserNotActiveResponse(true);
    }
    private UserEntity updateFields(UserEntity user, SetUserNotActiveRequest request) {
        var updatedUser = new UserEntity();
        updatedUser.setId(user.getId());
        updatedUser.setRoles(user.getRoles());
        updatedUser.setFirstName(user.getFirstName());
        updatedUser.setLastName(user.getLastName());
        updatedUser.setPhoneNumber(user.getPhoneNumber());
        updatedUser.setEmail(user.getEmail());
        updatedUser.setAddress(user.getAddress());
        updatedUser.setPassword(user.getPassword());
        updatedUser.setActive(false);
        return updatedUser;
    }
}
