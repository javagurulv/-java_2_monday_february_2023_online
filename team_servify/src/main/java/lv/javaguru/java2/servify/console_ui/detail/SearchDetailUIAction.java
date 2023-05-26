package lv.javaguru.java2.servify.console_ui.detail;

import lv.javaguru.java2.servify.console_ui.UIAction;
import lv.javaguru.java2.servify.core.domain.Detail;
import lv.javaguru.java2.servify.core.requests.SearchDetailRequest;
import lv.javaguru.java2.servify.core.responses.SearchDetailResponse;
import lv.javaguru.java2.servify.core.services.SearchDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Scanner;
@Component
public class SearchDetailUIAction implements UIAction {
    @Autowired
    private SearchDetailService searchDetailService;
    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter detail type: ");
        String detailType = scanner.nextLine();
        System.out.println("Enter detail side: ");
        String detailSide = scanner.nextLine();
        System.out.println("Enter detail price: ");
        String price = scanner.nextLine();
        BigDecimal detailPrice = validate(price);
        SearchDetailRequest request = new SearchDetailRequest(detailType, detailSide, detailPrice);
        SearchDetailResponse response = searchDetailService.execute(request);
        if (response.hasErrors()) {
            response.getErrors().forEach(coreError -> System.out.println("Error: " + coreError.getField() + " " + coreError.getMessage()));
        } else {
            response.getDetails().forEach(System.out::println);
        }
    }

    private static BigDecimal validate(String price) {
        if (price.isEmpty()) {
            return null;
        } else {
            return new BigDecimal(price);
        }
    }

    @Override
    public String getMenuItem() {
        return "Search detail";
    }
}
