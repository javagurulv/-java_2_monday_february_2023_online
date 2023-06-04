package lv.javaguru.java2.servify.console_ui.detail;

import lv.javaguru.java2.servify.console_ui.UIAction;
import lv.javaguru.java2.servify.core.dto.requests.GetAllDetailsRequest;
import lv.javaguru.java2.servify.core.dto.requests.RemoveDetailRequest;
import lv.javaguru.java2.servify.core.dto.responses.RemoveDetailResponse;
import lv.javaguru.java2.servify.core.services.details.GetAllDetailsService;
import lv.javaguru.java2.servify.core.services.details.RemoveDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class RemoveDetailUIAction implements UIAction {

    @Autowired private RemoveDetailService removeDetailService;
    @Autowired private GetAllDetailsService getAllDetailsService;

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        getAllDetailsService.getAll(new GetAllDetailsRequest()).getDetails().forEach(System.out::println);
        System.out.println("Enter detail id to remove (only use id that is visible in the list!): ");
        Long detailId = Long.parseLong(scanner.nextLine());
        RemoveDetailRequest request = new RemoveDetailRequest(detailId);
        RemoveDetailResponse response = removeDetailService.execute(request);
        if (response.hasErrors()) {
            response.getErrors().forEach(coreError -> System.out.println("Error "
                    + coreError.getField() + " " + coreError.getMessage()));
        } else {
            if (response.isDetailRemoved()) {
                System.out.println("Your detail was removed from repo.");
            } else {
                System.out.println("Your detail not removed from repo.");
            }
        }

    }

    @Override
    public String getMenuItem() {
        return "Remove detail from repository";
    }

}
