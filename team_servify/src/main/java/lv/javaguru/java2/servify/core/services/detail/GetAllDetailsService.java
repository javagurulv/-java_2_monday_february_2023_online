package lv.javaguru.java2.servify.core.services.detail;

import lv.javaguru.java2.servify.core.domain.Detail;
import lv.javaguru.java2.servify.core.database.DetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class GetAllDetailsService {

    @Autowired private DetailRepository detailRepository;

    public List<Detail> execute() {
        return detailRepository.getAllDetails();
    }

}
