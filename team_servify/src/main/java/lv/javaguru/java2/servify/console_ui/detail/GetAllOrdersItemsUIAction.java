package lv.javaguru.java2.servify.console_ui.detail;

import lv.javaguru.java2.servify.console_ui.UIAction;
import lv.javaguru.java2.servify.core.services.GetAllOrdersItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//@Component
public class GetAllOrdersItemsUIAction implements UIAction {
    @Autowired
    private GetAllOrdersItemsService getAllOrdersItemsService;


    @Override
    public void execute() {
        System.out.println("OrdersItems List");
        getAllOrdersItemsService.getAll().getOrderItems().forEach(System.out::println);
        System.out.println("List end");
    }

    @Override
    public String getMenuItem() {
        return "Show all OrderItems";
    }
}
