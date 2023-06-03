package lv.javaguru.java2.servify.console_ui.detail;

import lv.javaguru.java2.servify.console_ui.UIAction;
import lv.javaguru.java2.servify.core.dto.requests.AddDetailRequest;
import lv.javaguru.java2.servify.core.dto.responses.AddDetailResponse;
import lv.javaguru.java2.servify.core.services.details.AddDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Scanner;

@Component
public class AddDetailUIAction implements UIAction {
    @Autowired
    private AddDetailService addDetailService;

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter detail type:");
        String detailType = scanner.nextLine();
        System.out.println("Enter detail side:");
        String detailSide = scanner.nextLine();
        System.out.println("Enter detail price:");
        BigDecimal detailPrice = new BigDecimal(scanner.nextLine());
        AddDetailRequest request = new AddDetailRequest(detailType, detailSide, detailPrice);
        AddDetailResponse response = addDetailService.execute(request);

        if (response.hasErrors()) {
            response.getErrors().forEach(coreError ->
                    System.out.println("Error: " + coreError.getField() + " " + coreError.getMessage())
            );
        } else {
            System.out.println("New detail id was: " + response.newDetail().getId());
            System.out.println("Your detail was added to repository.");
        }
    }

    @Override
    public String getMenuItem() {
        return "Add detail to repository";
    }
}
