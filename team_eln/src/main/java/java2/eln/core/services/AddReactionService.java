package java2.eln.core.services;

import java2.eln.core.database.ReactionRepository;
import java2.eln.core.requests.AddReactionRequest;
import java2.eln.core.responses.AddReactionResponse;
import java2.eln.core.responses.errorPattern.CoreError;
import java2.eln.core.services.validators.AddReactionValidator;
import java2.eln.core.domain.ReactionData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AddReactionService {
    @Autowired
    private ReactionRepository reactionRepository;
    @Autowired
    private AddReactionValidator validator;

     public AddReactionResponse execute(AddReactionRequest addReactionRequest) {
        List<CoreError> errors = validator.validate(addReactionRequest);
         if (!errors.isEmpty()) {
             return new AddReactionResponse(errors);
         }
        String code = addReactionRequest.getCode();
        String name = addReactionRequest.getName();
        String filename = addReactionRequest.getFilename();

        ReactionData demoReactionLog = new ReactionData(code, name);
        additionOfMaterials(filename, demoReactionLog); // The Materials added to the ReactionData from the file
        additionOfConditions(filename, demoReactionLog); // The Reaction Conditions added to the ReactionData from the file

        demoReactionLog.calculateReactionYield();

        reactionRepository.addReaction(demoReactionLog);
        return new AddReactionResponse(demoReactionLog);
    }

    private void additionOfConditions(String filename, ReactionData demoReactionLog) {
        CreateConditionDataFromFile newConditions = new CreateConditionDataFromFile(filename);
        demoReactionLog.setConditions(newConditions.readFromFile());
    }

    // try regex !!!
    private void additionOfMaterials(String filename, ReactionData demoReactionLog) {
        CreateStructureFromFile newMaterial = new CreateStructureFromFile(filename);
        demoReactionLog.addStartingMaterial(newMaterial.readFromFile("SM1")); // starting material
        demoReactionLog.addStartingMaterial(newMaterial.readFromFile("SM2"));
        demoReactionLog.addStartingMaterial(newMaterial.readFromFile("SM3"));
        demoReactionLog.setMainProduct(newMaterial.readFromFile("MP")); // main product of the reaction
    }

}
