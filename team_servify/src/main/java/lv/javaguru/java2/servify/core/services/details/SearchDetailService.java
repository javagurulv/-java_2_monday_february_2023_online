package lv.javaguru.java2.servify.core.services.details;

//import javax.transaction.Transactional;
import lv.javaguru.java2.servify.core.database.DetailRepository;
import lv.javaguru.java2.servify.core.domain.Detail;
import lv.javaguru.java2.servify.core.dto.requests.SearchDetailRequest;
import lv.javaguru.java2.servify.core.dto.responses.CoreError;
import lv.javaguru.java2.servify.core.dto.responses.SearchDetailResponse;
import lv.javaguru.java2.servify.core.services.validators.SearchDetailRequestValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class SearchDetailService {
    @Autowired
    private DetailRepository detailRepository;
    @Autowired
    private SearchDetailRequestValidator validator;

    public SearchDetailResponse execute(SearchDetailRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new SearchDetailResponse(null, errors);
        }
        List<Detail> details = search(request);
        return new SearchDetailResponse(details, null);
    }

    private List<Detail> search(SearchDetailRequest request) {
        List<Detail> details = new ArrayList<>();
        if (request.isTypeProvided() && !request.isSideProvided() && !request.isPriceProvided()) {
            details = detailRepository.findByDetailType(request.getDetailType());
        }
        if (!request.isTypeProvided() && request.isSideProvided() && !request.isPriceProvided()) {
            details = detailRepository.findByDetailSide(request.getDetailSide());
        }
        if (!request.isTypeProvided() && !request.isSideProvided() && request.isPriceProvided()) {
            details = detailRepository.findByDetailPrice(request.getDetailPrice());
        }
        if (request.isTypeProvided() && request.isSideProvided() && !request.isPriceProvided()) {
            details = detailRepository.findByDetailTypeSide(request.getDetailType(), request.getDetailSide());
        }
        if (request.isTypeProvided() && !request.isSideProvided() && request.isPriceProvided()) {
            details = detailRepository.findByDetailTypePrice(request.getDetailType(), request.getDetailPrice());
        }

        return details;
    }
}
