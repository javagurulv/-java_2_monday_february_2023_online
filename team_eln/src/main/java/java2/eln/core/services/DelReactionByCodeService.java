package java2.eln.core.services;

import java2.eln.core.database.ReactionRepository;
import java2.eln.core.requests.DeleteReactionByCodeRequest;
import java2.eln.core.responses.errorPattern.CoreError;
import java2.eln.core.responses.DeleteReactionResponse;
import java2.eln.core.services.validators.DelReactionByCodeValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DelReactionByCodeService {

    @Autowired
    ReactionRepository reactionRepository;

    @Autowired
    DelReactionByCodeValidator delReactionByCodeValidator;

//    public DelReactionService(DatabaseIM databaseIM, DelReactionValidator delReactionValidator) {
//        this.databaseIM = databaseIM;
//        this.delReactionValidator = delReactionValidator;
//    }

    public DeleteReactionResponse execute (DeleteReactionByCodeRequest deleteReactionByCodeRequest){
        List<CoreError> errors = delReactionByCodeValidator.validate(deleteReactionByCodeRequest);
        if (!errors.isEmpty()) {
            return new DeleteReactionResponse(errors);
        }
        String code = deleteReactionByCodeRequest.getCode();
        return new DeleteReactionResponse(reactionRepository.delReactionByCode(code));
    }

}
