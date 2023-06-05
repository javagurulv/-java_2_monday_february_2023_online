package lv.javaguru.java2.servify.core.services.users;

//import javax.transaction.Transactional;
import lv.javaguru.java2.servify.core.database.UserRepository;
import lv.javaguru.java2.servify.core.domain.UserEntity;
import lv.javaguru.java2.servify.core.dto.requests.AddUserRequest;
import lv.javaguru.java2.servify.core.dto.responses.AddUserResponse;
import lv.javaguru.java2.servify.core.dto.responses.CoreError;
import lv.javaguru.java2.servify.core.services.validators.AddUserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AddUserService {

    @Autowired private UserRepository userDB;
    @Autowired private AddUserValidator validator;

    public AddUserResponse execute(AddUserRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new AddUserResponse(errors);
        }

        UserEntity user = new UserEntity(request.getFirstName(),
                                        request.getLastName(),
                                        request.getEmail(),
                                        request.getPhoneNumber());
        userDB.save(user);

        return new AddUserResponse(user);
    }
}
