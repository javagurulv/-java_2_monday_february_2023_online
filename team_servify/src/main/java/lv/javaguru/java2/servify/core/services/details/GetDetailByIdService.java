package lv.javaguru.java2.servify.core.services.details;

//import javax.transaction.Transactional;
import lv.javaguru.java2.servify.core.database.DetailRepository;
import lv.javaguru.java2.servify.core.domain.Detail;
import lv.javaguru.java2.servify.core.dto.DetailDTO;
import lv.javaguru.java2.servify.core.dto.responses.GetDetailByIdResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class GetDetailByIdService {
    @Autowired
    private DetailRepository detailRepository;

    public GetDetailByIdResponse getById(Long id) {
        return detailRepository.findById(id)
                .map(this::convert)
                .map(GetDetailByIdResponse::new)
                .orElseThrow(() -> new IllegalArgumentException("Detail with id " + id + " not found"));
    }

    private DetailDTO convert(Detail detail) {
        return new DetailDTO(detail.getId(),
                            detail.getType(),
                            detail.getSide(),
                            detail.getPrice().toString());
    }
}
