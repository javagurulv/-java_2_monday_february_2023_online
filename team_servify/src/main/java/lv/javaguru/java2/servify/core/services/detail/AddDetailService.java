package lv.javaguru.java2.servify.core.services.detail;

import lv.javaguru.java2.servify.core.requests.detail.AddDetailRequest;
import lv.javaguru.java2.servify.core.responses.CoreError;
import lv.javaguru.java2.servify.core.responses.detail.AddDetailResponse;
import lv.javaguru.java2.servify.core.validators.AddDetailValidator;
import lv.javaguru.java2.servify.domain.PriceList;
import lv.javaguru.java2.servify.domain.detail.Detail;
import lv.javaguru.java2.servify.core.database.DetailDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddDetailService {

    @Autowired
    private DetailDatabase database;
    @Autowired
    private PriceList priceList;
    @Autowired
    private AddDetailValidator validator;

    public AddDetailResponse execute(AddDetailRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new AddDetailResponse(errors);
        }
        Detail newDetail = new Detail(priceList.findById(request.getId()).get().getType(),
                                      priceList.findById(request.getId()).get().getSide(),
                                      priceList.findById(request.getId()).get().getPrice());
        database.save(newDetail);
        return new AddDetailResponse(newDetail);
    }
}
