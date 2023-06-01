package java2.eln.console_ui;

import java2.eln.core.requests.DeleteReactionByCodeRequest;
import java2.eln.core.responses.DeleteReactionResponse;
import java2.eln.core.services.DelReactionByCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class DelReactionByCodeUIAction implements UIAction{

    @Autowired
    DelReactionByCodeService delReactionByCodeService;

//    public DelReactionUIAction(DelReactionService delReactionService) {
//        this.delReactionService = delReactionService;
//    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Reaction Code to delete: ");
        String  reactionCode = scanner.nextLine();

        DeleteReactionByCodeRequest deleteReactionByCodeRequest = new DeleteReactionByCodeRequest(reactionCode);
        DeleteReactionResponse deleteReactionResponse = delReactionByCodeService.execute(deleteReactionByCodeRequest);

        if (deleteReactionResponse.hasErrors()) {
            deleteReactionResponse.getErrors().forEach(coreError ->
                    System.out.println("InputError: " + coreError.getField() + " " + coreError.getMessage())
            );
        } else {
            System.out.printf("Reaction %s has been deleted -> %s", reactionCode, deleteReactionResponse.getDelResult());
        }

    }
}
