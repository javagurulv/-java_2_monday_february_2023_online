package java2.eln.console_ui;

import java2.eln.core.requests.DeleteReactionByCodeRequest;
import java2.eln.core.requests.DeleteReactionByIdRequest;
import java2.eln.core.responses.DeleteReactionResponse;
import java2.eln.core.services.DelReactionByCodeService;
import java2.eln.core.services.DelReactionByIDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class DelReactionByIdUIAction implements UIAction{

    @Autowired
    DelReactionByIDService delReactionByIDService;

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Reaction ID to delete: ");
        String  reactionId = scanner.nextLine();

        DeleteReactionByIdRequest deleteReactionByIdRequest = new DeleteReactionByIdRequest(reactionId);
        DeleteReactionResponse deleteReactionResponse = delReactionByIDService.execute(deleteReactionByIdRequest);

        if (deleteReactionResponse.hasErrors()) {
            deleteReactionResponse.getErrors().forEach(coreError ->
                    System.out.println("InputError: " + coreError.getField() + " " + coreError.getMessage())
            );
        } else {
            System.out.printf("Reaction %s has been deleted -> %s", reactionId, deleteReactionResponse.getDelResult());
        }

    }
}
