package lv.javaguru.java2.servify.core.services.detail;

import lv.javaguru.java2.servify.domain.Detail;
import lv.javaguru.java2.servify.core.database.DetailDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetAllDetailsService {

    @Autowired private DetailDatabase database;

    public List<Detail> execute() {
        return database.getAllDetails();
    }

}
