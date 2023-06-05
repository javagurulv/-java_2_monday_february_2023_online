package lv.javaguru.java2.servify.console_ui.detail;

import lv.javaguru.java2.servify.console_ui.UIAction;
import lv.javaguru.java2.servify.core.dto.requests.UpdateDetailRequest;
import lv.javaguru.java2.servify.core.dto.responses.UpdateDetailResponse;
import lv.javaguru.java2.servify.core.services.details.GetDetailByIdService;
import lv.javaguru.java2.servify.core.services.details.UpdateDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Scanner;
@Component
public class UpdateDetailByIdUIAction implements UIAction {
//    @Autowired
//    private GetDetailByIdService getDetailByIdService;
    @Autowired
    private UpdateDetailService updateDetailService;

    @Override
    public void execute() {
        var input = new Scanner(System.in);
        System.out.println("Enter detail ID");
        var id = Long.valueOf(input.nextLine());
        System.out.println("Enter new Detail's type");
        var type = input.nextLine();
        System.out.println("Enter new Detail's side");
        var side = input.nextLine();
        System.out.println("Enter new Detail's price");
        var stringPrice = input.nextLine();
        BigDecimal price = new BigDecimal(stringPrice);
        var updateRequest = new UpdateDetailRequest(id, type, side, price);

        UpdateDetailResponse response = updateDetailService.update(updateRequest);
        if (response.hasErrors()) {
            response.getErrors().forEach(coreError ->
                    System.out.println("Error: " + coreError.getField() + " " + coreError.getMessage())
            );
        } else {
            System.out.println("Detail with ID: " + updateRequest.getId() + " was updated");
        }
    }

    @Override
    public String getMenuItem() {
        return "Update Detail by ID";
    }
}
