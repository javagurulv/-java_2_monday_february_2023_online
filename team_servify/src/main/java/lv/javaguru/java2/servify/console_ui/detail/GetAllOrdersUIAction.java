package lv.javaguru.java2.servify.console_ui.detail;

import lv.javaguru.java2.servify.console_ui.UIAction;
import lv.javaguru.java2.servify.core.services.GetAllOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GetAllOrdersUIAction implements UIAction {
    @Autowired
    private GetAllOrdersService getAllOrdersService;
    @Override
    public void execute() {
        System.out.println("Order list");
        getAllOrdersService.getAll().getOrders().forEach(System.out::println);
        System.out.println("List end");
    }

    @Override
    public String getMenuItem() {
        return "Get All orders";
    }
}
