package lv.javaguru.java2.servify.console_ui.detail;

import lv.javaguru.java2.servify.console_ui.UIAction;
import lv.javaguru.java2.servify.core.dto.requests.UpdateDetailRequest;
import lv.javaguru.java2.servify.core.services.GetDetailByIdService;
import lv.javaguru.java2.servify.core.services.UpdateDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;
@Component
public class UpdateDetailByIdUIAction implements UIAction {
    @Autowired
    private GetDetailByIdService getDetailByIdService;
    @Autowired
    private UpdateDetailService updateDetailService;

    @Override
    public void execute() {
        var input = new Scanner(System.in);
        System.out.println("Enter detail ID");
        var id = Long.valueOf(input.nextLine());
        var foundDetailResponse = getDetailByIdService.getById(id);
        var detail = foundDetailResponse.getDetail();
        System.out.println("Enter new Detail's type");
        var type = input.nextLine();
        System.out.println("Enter new Detail's side");
        var side = input.nextLine();
        System.out.println("Enter new Detail's price");
        var price = input.nextLine();
        var updateRequest = new UpdateDetailRequest();

        updateRequest.setId(detail.getId());

        var updatedType = getUpdatedValue(type, detail.getType());
        var updatedSide = getUpdatedValue(side, detail.getSide());
        var updatedPrice = getUpdatedValue(price, detail.getPrice());

        updateRequest.setType(updatedType);
        updateRequest.setSide(updatedSide);
        updateRequest.setPrice(updatedPrice);

        updateDetailService.update(updateRequest);
    }

    private String getUpdatedValue(String newValue, String oldValue) {
        return newValue.isEmpty() ? oldValue : newValue;
    }

    @Override
    public String getMenuItem() {
        return "Update Detail by ID";
    }
}
