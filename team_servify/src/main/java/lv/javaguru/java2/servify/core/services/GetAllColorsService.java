package lv.javaguru.java2.servify.core.services;

import jakarta.transaction.Transactional;
import lv.javaguru.java2.servify.core.database.ColorRepository;
import lv.javaguru.java2.servify.core.dto.responses.GetAllColorsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class GetAllColorsService {
    @Autowired
    private ColorRepository colorRepository;

    public GetAllColorsResponse getAll() {
        var colors = colorRepository.getAll();
        return new GetAllColorsResponse(colors);
    }
}
