package shop.core.services.actions.shared;

import org.springframework.stereotype.Component;

@Component
public class ExitService {

    public void execute() {
        System.exit(0);
    }

}
