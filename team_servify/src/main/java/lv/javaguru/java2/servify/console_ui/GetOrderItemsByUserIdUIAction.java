package lv.javaguru.java2.servify.console_ui;


import lv.javaguru.java2.servify.console_ui.UIAction;
import lv.javaguru.java2.servify.core.services.GetOrderItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class GetOrderItemsByUserIdUIAction implements UIAction {
    @Autowired
    private GetOrderItemsService getOrderItemsService;


    @Override
    public void execute() {
        var input = new Scanner(System.in);
        System.out.println("Enter userId");
        var userId = Long.parseLong(input.nextLine());
        System.out.println("Items list");
        getOrderItemsService.getOrderItemsByUserId(userId).getOrderItems().forEach(System.out::println);
        System.out.println("List end");
    }

    @Override
    public String getMenuItem() {
        return "Get OrderItems by UserId";
    }
}
