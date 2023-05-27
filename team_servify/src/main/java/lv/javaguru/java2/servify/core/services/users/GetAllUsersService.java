package lv.javaguru.java2.servify.core.services.users;

import lv.javaguru.java2.servify.core.database.UserRepository;
import lv.javaguru.java2.servify.core.domain.UserEntity;
import lv.javaguru.java2.servify.core.dto.UserDTO;
import lv.javaguru.java2.servify.core.dto.responses.GetAllUsersResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GetAllUsersService {
    @Autowired
    private UserRepository userRepository;

    public GetAllUsersResponse getAll() {
        var dtos = userRepository.getAllUsers().stream()
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
                entity.isInactive(),
                entity.getUserType());
                //entity.getUserType().iterator().next());
    }
}
