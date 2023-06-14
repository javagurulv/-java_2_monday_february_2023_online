package java2.eln.core.services.validators;

import java2.eln.core.database.ReactionRepository;
import java2.eln.core.requests.DeleteReactionByCodeRequest;
import java2.eln.core.responses.errorPattern.CoreError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class DelReactionByCodeValidator {
    @Autowired
    ReactionRepository reactionRepository;

    public List<CoreError> validate(DeleteReactionByCodeRequest request) {
        List<CoreError> errors = new ArrayList<>();
        codeValidate(request).ifPresent(errors::add);
        reactionIsPresentValidate(request).ifPresent(errors::add);
        return errors;
    }

    private Optional<CoreError> codeValidate (DeleteReactionByCodeRequest deleteReactionByCodeRequest){
        return (deleteReactionByCodeRequest.getCode() == null || deleteReactionByCodeRequest.getCode().isBlank())
                ? Optional.of(new CoreError("Reaction Code", "Must not be empty!"))
                : Optional.empty();
    }
    private Optional<CoreError> reactionIsPresentValidate (DeleteReactionByCodeRequest deleteReactionByCodeRequest){
        return (reactionRepository.hasReactionWithCode(deleteReactionByCodeRequest.getCode()))
                ? Optional.empty()
                : Optional.of(new CoreError("Reaction code not found", "enter the code of the reaction existing in the database"));
    }
}
