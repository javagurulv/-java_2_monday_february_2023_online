package lv.javaguru.java2.servify.core.services.detail;

import lv.javaguru.java2.servify.core.database.DetailDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RemoveDetailService {

    @Autowired private DetailDatabase database;

    public void execute(Long id) {
        database.deleteById(id);
    }

}
