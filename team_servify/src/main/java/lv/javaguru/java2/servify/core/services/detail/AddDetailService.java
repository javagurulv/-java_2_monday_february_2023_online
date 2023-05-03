package lv.javaguru.java2.servify.core.services.detail;

import lv.javaguru.java2.servify.domain.Detail;
import lv.javaguru.java2.servify.core.database.DetailDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AddDetailService {

    @Autowired private DetailDatabase database;

    public void execute(Detail detail) {
        database.save(detail);
    }

}
