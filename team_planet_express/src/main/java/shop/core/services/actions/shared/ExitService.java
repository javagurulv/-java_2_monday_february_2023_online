package shop.core.services.actions.shared;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class ExitService {

    public void execute() {
        System.exit(0);
    }

}
