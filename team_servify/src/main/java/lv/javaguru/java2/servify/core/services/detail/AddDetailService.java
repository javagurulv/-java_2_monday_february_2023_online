package lv.javaguru.java2.servify.core.services.detail;

import lv.javaguru.java2.servify.core.requests.detail.AddDetailRequest;
import lv.javaguru.java2.servify.core.responses.CoreError;
import lv.javaguru.java2.servify.core.responses.detail.AddDetailResponse;
import lv.javaguru.java2.servify.core.validators.AddDetailValidator;
import lv.javaguru.java2.servify.domain.FieldTitle;
import lv.javaguru.java2.servify.domain.PriceList;
import lv.javaguru.java2.servify.domain.detail.Detail;
import lv.javaguru.java2.servify.core.database.DetailDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class AddDetailService {

    @Autowired private DetailDatabase database;
    @Autowired private PriceList priceList;
    @Autowired private AddDetailValidator validator;

    public AddDetailResponse execute(AddDetailRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new AddDetailResponse(errors);
        }
        Optional<Detail> detailOptional = priceList.findById(request.getId());
        if (detailOptional.isEmpty()) {
            errors.add(new CoreError(FieldTitle.ID, "Not valid ID"));
            return new AddDetailResponse(errors);
        }
        Detail newDetail = detailOptional.get();
        database.save(newDetail);
        return new AddDetailResponse(newDetail);
    }

}
