package lv.javaguru.java2.servify.core.services.details;

//import javax.transaction.Transactional;
import lv.javaguru.java2.servify.core.domain.Detail;
import lv.javaguru.java2.servify.core.database.DetailRepository;
import lv.javaguru.java2.servify.core.dto.DetailDTO;
import lv.javaguru.java2.servify.core.dto.responses.GetAllDetailResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class GetAllDetailsService {

    @Autowired private DetailRepository detailRepository;

    public GetAllDetailResponse getAll() {
        var dtos = detailRepository.getAllDetails().stream()
                .map(this::convert)
                .toList();
        return new GetAllDetailResponse(dtos);
    }
    private DetailDTO convert(Detail entity) {
        return new DetailDTO(entity.getId(),
                entity.getType(),
                entity.getSide(),
                entity.getPrice().toString());
    }
}
