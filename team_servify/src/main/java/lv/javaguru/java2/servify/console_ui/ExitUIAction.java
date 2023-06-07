package lv.javaguru.java2.servify.console_ui;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ExitUIAction implements UIAction {
    @Override
    public void execute() {
        System.out.println("Good bye!");
        System.exit(0);
    }

    @Override
    public String getMenuItem() {
        return "Exit";
    }

}
