package lv.javaguru.java2.servify.core.services.details;

import lv.javaguru.java2.servify.core.database.jpa.JpaDetailRepository;
import lv.javaguru.java2.servify.core.domain.Detail;
import lv.javaguru.java2.servify.core.domain.FieldTitle;
import lv.javaguru.java2.servify.core.dto.DetailDTO;
import lv.javaguru.java2.servify.core.dto.requests.GetDetailRequest;
import lv.javaguru.java2.servify.core.dto.responses.CoreError;
import lv.javaguru.java2.servify.core.dto.responses.GetDetailResponse;
import lv.javaguru.java2.servify.core.services.validators.GetDetailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class GetDetailService {
    @Autowired
    private JpaDetailRepository detailRepository;
    @Autowired
    private GetDetailValidator validator;

    public GetDetailResponse execute(GetDetailRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new GetDetailResponse(errors);
        }
        return detailRepository.findById(request.getId())
                .map(this::convert)
                .map(GetDetailResponse::new)
                .orElseGet(() -> {
                    errors.add(new CoreError(FieldTitle.ID, "Id not found"));
                    return new GetDetailResponse(errors);
                });
    }

    private DetailDTO convert(Detail detail) {
        return new DetailDTO(detail.getId(),
                detail.getType(),
                detail.getSide(),
                detail.getPrice().toString());
    }
}
