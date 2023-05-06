package lv.javaguru.java2.servify.console_ui.detail;

import lv.javaguru.java2.servify.console_ui.UIAction;
import lv.javaguru.java2.servify.core.services.detail.GetAllDetailsService;
import lv.javaguru.java2.servify.domain.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GetAllDetailsFromOrderUIAction implements UIAction {

    @Autowired private GetAllDetailsService getAllDetailsService;

    @Override
    public void execute() {
        System.out.println("Detail list: ");
        getAllDetailsService.execute().forEach(System.out::println);
        System.out.println("Detail list end.");
    }

    @Override
    public String getMenuItem() {
        return "Show all details in order";
    }

    @Override
    public List<UserType> getAccessUserByType() {
        return new ArrayList<>(List.of(
                UserType.CUSTOMER,
                UserType.MANAGER
        ));
    }
}
