package lv.javaguru.java2.servify.console_ui.detail;

import lv.javaguru.java2.servify.console_ui.UIAction;
import lv.javaguru.java2.servify.core.dto.requests.GetAllDetailsRequest;
import lv.javaguru.java2.servify.core.services.details.GetAllDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//@Component
public class GetAllDetailsUIAction implements UIAction {

    @Autowired private GetAllDetailsService getAllDetailsService;

    @Override
    public void execute() {
        System.out.println("Detail list: ");
        getAllDetailsService.getAll().getDetails().forEach(System.out::println);
        System.out.println("Detail list end.");
    }

    @Override
    public String getMenuItem() {
        return "Show Price list";
    }
}
