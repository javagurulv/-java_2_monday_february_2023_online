package lv.javaguru.java2.servify.core.services;

import lv.javaguru.java2.servify.core.database.jpa.JpaColorRepository;
import lv.javaguru.java2.servify.core.dto.responses.GetAllColorsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class GetAllColorsService {
    @Autowired
    private JpaColorRepository colorRepository;

    public GetAllColorsResponse getAll() {
        var colors = colorRepository.findAll();
        return new GetAllColorsResponse(colors);
    }
}
