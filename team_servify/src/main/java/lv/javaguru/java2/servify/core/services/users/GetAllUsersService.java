package lv.javaguru.java2.servify.core.services.users;

//import javax.transaction.Transactional;
import lv.javaguru.java2.servify.core.database.UserRepository;
import lv.javaguru.java2.servify.core.database.jpa.JpaUserRepository;
import lv.javaguru.java2.servify.core.domain.UserEntity;
import lv.javaguru.java2.servify.core.dto.UserDTO;
import lv.javaguru.java2.servify.core.dto.requests.GetAllUsersRequest;
import lv.javaguru.java2.servify.core.dto.responses.GetAllUsersResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class GetAllUsersService {
    @Autowired
    private JpaUserRepository userRepository;

    public GetAllUsersResponse execute(GetAllUsersRequest request) {
        var dtos = userRepository.findAll().stream()
                .map(this::convert)
                .toList();
        return new GetAllUsersResponse(dtos);
    }

    private UserDTO convert(UserEntity entity) {
        return new UserDTO(entity.getId(),
                entity.getFirstName(),
                entity.getLastName(),
                entity.getEmail(),
                entity.getPhoneNumber(),
                entity.getAddress(),
                entity.isActive(),
                entity.getAuthorities().toString()
        );
    }
}
