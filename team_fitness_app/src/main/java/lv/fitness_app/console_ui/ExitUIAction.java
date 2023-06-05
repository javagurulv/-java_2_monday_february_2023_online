package lv.fitness_app.console_ui;

import org.springframework.stereotype.Component;

@Component
public class ExitUIAction implements UIAction {

    @Override
    public void execute() {
        System.out.println("Goodbye!");
        System.exit(0);
    }
}
