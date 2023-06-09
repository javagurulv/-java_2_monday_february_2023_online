package lv.fitness_app.core.services;

import lv.fitness_app.core.database.UserRepository;
import lv.fitness_app.core.requests.GetAllUsersRequest;
import lv.fitness_app.core.responses.GetAllUsersResponse;
import lv.fitness_app.core.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class GetAllUsersService {

    @Autowired
    private UserRepository userRepository;


    public GetAllUsersResponse execute(GetAllUsersRequest request) {
        List<User> users = userRepository.getAllUsers();
        return new GetAllUsersResponse(users);
    }
}
