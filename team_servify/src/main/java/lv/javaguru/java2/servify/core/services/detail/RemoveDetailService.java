package lv.javaguru.java2.servify.core.services.detail;

import lv.javaguru.java2.servify.core.database.DetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RemoveDetailService {

    @Autowired private DetailRepository detailRepository;

    public void execute(Long id) {
        detailRepository.deleteById(id);
    }

}
