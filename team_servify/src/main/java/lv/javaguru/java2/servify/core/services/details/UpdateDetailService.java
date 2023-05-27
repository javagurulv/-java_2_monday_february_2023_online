package lv.javaguru.java2.servify.core.services.details;

import lv.javaguru.java2.servify.core.database.DetailRepository;
import lv.javaguru.java2.servify.core.domain.Detail;
import lv.javaguru.java2.servify.core.dto.requests.UpdateDetailRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class UpdateDetailService {
    @Autowired
    private DetailRepository detailRepository;

    public void update(UpdateDetailRequest request) {
        detailRepository.findById(request.getId())
                .map(detail -> updateFields(detail, request))
                .ifPresent(detailRepository::save);
    }

    private Detail updateFields(Detail detail, UpdateDetailRequest request) {
        var updatedDetail = new Detail();
        updatedDetail.setId(detail.getId());
        updatedDetail.setType(request.getType());
        updatedDetail.setSide(request.getSide());
        updatedDetail.setPrice(new BigDecimal(request.getPrice()));
        return updatedDetail;
    }
}
