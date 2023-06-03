package lv.javaguru.java2.servify.console_ui;

import lv.javaguru.java2.servify.core.services.GetAllColorsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GetAllColorsUIAction implements UIAction {

    @Autowired
    private GetAllColorsService getAllColorsService;
    @Override
    public void execute() {
        System.out.println("Colors list: ");
        getAllColorsService.getAll().getColors().forEach(System.out::println);
        System.out.println("List end");
    }

    @Override
    public String getMenuItem() {
        return "Show All colors";
    }
}
