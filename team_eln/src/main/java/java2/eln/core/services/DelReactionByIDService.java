package java2.eln.core.services;

import java2.eln.core.database.DatabaseIM;
import java2.eln.core.requests.DeleteReactionByIdRequest;
import java2.eln.core.responses.DeleteReactionResponse;
import java2.eln.core.responses.errorPattern.CoreError;
import java2.eln.core.services.validators.DelReactionByCodeValidator;
import java2.eln.core.services.validators.DelReactionByIdValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DelReactionByIDService {

    @Autowired
    DatabaseIM databaseIM;

    @Autowired
    DelReactionByIdValidator delReactionByIdValidator;

    public DeleteReactionResponse execute (DeleteReactionByIdRequest deleteReactionByIdRequest){
        List<CoreError> errors = delReactionByIdValidator.validate(deleteReactionByIdRequest);
        if (!errors.isEmpty()) {
            return new DeleteReactionResponse(errors);
        }
        int id = deleteReactionByIdRequest.getID();
        return new DeleteReactionResponse(databaseIM.delReactionByID(id));
    }
}
