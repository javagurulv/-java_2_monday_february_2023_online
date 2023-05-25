package lv.javaguru.java2.servify.core.services.detail;

import lv.javaguru.java2.servify.core.requests.detail.AddDetailRequest;
import lv.javaguru.java2.servify.core.responses.CoreError;
import lv.javaguru.java2.servify.core.responses.AddDetailResponse;
import lv.javaguru.java2.servify.core.validators.AddDetailValidator;
import lv.javaguru.java2.servify.core.domain.Detail;
import lv.javaguru.java2.servify.core.database.DetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddDetailService {

    @Autowired
    private DetailRepository detailRepository;

    @Autowired
    private AddDetailValidator validator;

    public AddDetailResponse execute(AddDetailRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new AddDetailResponse(errors);
        }
        Detail newDetail = new Detail(request.getDetailType(), request.getDetailSide(), request.getDetailPrice());
        detailRepository.save(newDetail);
        return new AddDetailResponse(newDetail);
    }
}
