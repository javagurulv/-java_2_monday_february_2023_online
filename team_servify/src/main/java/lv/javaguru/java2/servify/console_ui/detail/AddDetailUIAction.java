package lv.javaguru.java2.servify.console_ui.detail;

import lv.javaguru.java2.servify.console_ui.UIAction;
import lv.javaguru.java2.servify.core.requests.detail.AddDetailRequest;
import lv.javaguru.java2.servify.core.responses.detail.AddDetailResponse;
import lv.javaguru.java2.servify.core.services.detail.AddDetailService;
import lv.javaguru.java2.servify.domain.PriceList;
import lv.javaguru.java2.servify.domain.detail.Detail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Component
public class AddDetailUIAction implements UIAction {

    @Autowired
    private AddDetailService addDetailService;
    private final PriceList priceList = new PriceList();

    @Override
    public void execute() {
        List<Detail> details = priceList.getDetailPricesList();
        details.forEach(System.out::println);
        System.out.println("Choose the item from the list to add to the cart");
        Scanner scanner = new Scanner(System.in);

        AddDetailRequest request = new AddDetailRequest(Long.parseLong(scanner.nextLine()));
        AddDetailResponse response = addDetailService.execute(request);

        if (response.hasErrors()) {
            response.getErrors().forEach(coreError ->
                    System.out.println("Error: " + coreError.getField() + " " + coreError.getMessage()));
        } else {
            Optional<Detail> detailOptional = priceList.findById(request.getId());
            if (detailOptional.isEmpty()) {
                System.out.println("Detail is not added.");
            } else {
                System.out.println(detailOptional.get() + " was added to list.");
            }
        }
    }
}
