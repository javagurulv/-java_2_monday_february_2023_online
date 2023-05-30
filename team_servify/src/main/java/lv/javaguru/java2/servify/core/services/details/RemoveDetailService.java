package lv.javaguru.java2.servify.core.services.details;

import javax.transaction.Transactional;
import lv.javaguru.java2.servify.core.database.DetailRepository;
import lv.javaguru.java2.servify.core.dto.requests.RemoveDetailRequest;
import lv.javaguru.java2.servify.core.dto.responses.CoreError;
import lv.javaguru.java2.servify.core.dto.responses.RemoveDetailResponse;
import lv.javaguru.java2.servify.core.services.validators.RemoveDetailRequestValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class RemoveDetailService {

    @Autowired private DetailRepository detailRepository;
    @Autowired private RemoveDetailRequestValidator validator;

    public RemoveDetailResponse execute(RemoveDetailRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new RemoveDetailResponse(errors);
        }
        boolean isDetailRemoved =  detailRepository.deleteById(request.getId());
        return new RemoveDetailResponse(isDetailRemoved);
    }

}
