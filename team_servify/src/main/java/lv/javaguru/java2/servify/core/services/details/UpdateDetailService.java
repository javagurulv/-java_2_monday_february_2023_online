package lv.javaguru.java2.servify.core.services.details;

//import javax.transaction.Transactional;
import lv.javaguru.java2.servify.core.database.DetailRepository;
import lv.javaguru.java2.servify.core.database.jpa.JpaDetailRepository;
import lv.javaguru.java2.servify.core.domain.Detail;
import lv.javaguru.java2.servify.core.domain.FieldTitle;
import lv.javaguru.java2.servify.core.dto.requests.UpdateDetailRequest;
import lv.javaguru.java2.servify.core.dto.responses.CoreError;
import lv.javaguru.java2.servify.core.dto.responses.UpdateDetailResponse;
import lv.javaguru.java2.servify.core.services.validators.UpdateDetailRequestValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@Transactional
public class UpdateDetailService {
    @Autowired
    private JpaDetailRepository detailRepository;
    @Autowired private UpdateDetailRequestValidator validator;

    public UpdateDetailResponse update(UpdateDetailRequest request) {
        List<CoreError> errors = validator.validate(request);
        if(!errors.isEmpty()) {
            return new UpdateDetailResponse(errors);
        }
        return detailRepository.findById(request.getId())
                .map(detail -> {
                    detail.setId(detail.getId());
                    detail.setType(request.getType());
                    detail.setSide(request.getSide());
                    detail.setPrice(new BigDecimal(String.valueOf(request.getPrice())));
                    return new UpdateDetailResponse(detail);
                })
                .orElseGet(() -> {
                    errors.add(new CoreError(FieldTitle.ID, "Not found!"));
                    return new UpdateDetailResponse(errors);
                });
    }

    private Detail updateFields(Detail detail, UpdateDetailRequest request) {
        var updatedDetail = new Detail();
        updatedDetail.setId(detail.getId());
        updatedDetail.setType(request.getType());
        updatedDetail.setSide(request.getSide());
        updatedDetail.setPrice(new BigDecimal(String.valueOf(request.getPrice())));
        return updatedDetail;
    }
}
