package lv.javaguru.java2.servify.core.database;

import lv.javaguru.java2.servify.core.domain.Detail;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


//@Repository
public class InMemoryDetailRepositoryImpl implements DetailRepository {

    private Long nextId = 1L;
    private final List<Detail> details = new ArrayList<>();

    @Override
    public void save(Detail detail) {
        if (detail.getId() != null) {
            updateExistDetail(detail);
        } else {
            saveNewDetail(detail);
        }
    }

    private void saveNewDetail(Detail detail) {
        detail.setId(nextId);
        nextId++;
        details.add(detail);
    }

    private void updateExistDetail(Detail detail) {
        for (int i = 0; i < details.size(); i++) {
            var existDetail = details.get(i);
            if (existDetail.getId().equals(detail.getId())) {
                details.set(i, detail);
            }
        }
    }

    @Override
    public boolean deleteById(Long id) {
        boolean isDetailDeleted = false;
        Optional<Detail> detailToDeleteOpt = details.stream()
                .filter(detail -> detail.getId().equals(id))
                .findFirst();
        if (detailToDeleteOpt.isPresent()) {
            Detail detailToRemove = detailToDeleteOpt.get();
            isDetailDeleted = details.remove(detailToRemove);
        }
        return isDetailDeleted;
    }

    @Override
    public Optional<Detail> findById(Long id) {
        return details.stream()
                .filter(detail -> detail.getId().equals(id))
                .findFirst();
    }

    @Override
    public List<Detail> getAllDetails() {
        return details;
    }

    @Override
    public List<Detail> findByDetailType(String detailType) {
        return null;
    }

    @Override
    public List<Detail> findByDetailSide(String detailSide) {
        return null;
    }

    @Override
    public List<Detail> findByDetailTypeSide(String detailType, String detailSide) {
        return null;
    }

    @Override
    public List<Detail> findByDetailPrice(BigDecimal detailPrice) {
        return null;
    }

    @Override
    public List<Detail> findByDetailTypePrice(String detailType, BigDecimal detailPrice) {
        return null;
    }

}
