package java2.eln.core.services.validators;

import java2.eln.core.database.ReactionRepository;
import java2.eln.core.requests.DeleteReactionByIdRequest;
import java2.eln.core.responses.errorPattern.CoreError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class DelReactionByIdValidator {
    @Autowired
    ReactionRepository reactionRepository;

    private Optional<CoreError> IdValidate (DeleteReactionByIdRequest deleteReactionByIdRequest){
        return (deleteReactionByIdRequest.getID() == null)
                ? Optional.of(new CoreError("Reaction ID", "Must not be NULL!"))
                : Optional.empty();
    }
    private Optional<CoreError> reactionIsPresentValidate (DeleteReactionByIdRequest deleteReactionByIdRequest){
        return (reactionRepository.hasReactionWithId(deleteReactionByIdRequest.getID()))
                ? Optional.empty()
                : Optional.of(new CoreError("Reaction code not found", "enter the code of the reaction existing in the database"));
    }

    public List<CoreError> validate(DeleteReactionByIdRequest deleteReactionByIdRequest) {
        List<CoreError> errors = new ArrayList<>();
        IdValidate(deleteReactionByIdRequest).ifPresent(errors::add);
        reactionIsPresentValidate(deleteReactionByIdRequest).ifPresent(errors::add);
        return errors;
    }
}
