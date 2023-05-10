package lv.javaguru.java2.servify.core.services.detail;

import lv.javaguru.java2.servify.domain.detail.Detail;
import lv.javaguru.java2.servify.core.database.DetailDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class GetAllDetailsService {

    @Autowired private DetailDatabase database;

    public List<Detail> execute() {
        return database.getAllDetails();
    }

}
