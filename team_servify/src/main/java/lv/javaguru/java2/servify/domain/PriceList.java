package lv.javaguru.java2.servify.domain;

import lv.javaguru.java2.servify.domain.detail.Detail;
import lv.javaguru.java2.servify.domain.detail.DetailSideEnum;
import lv.javaguru.java2.servify.domain.detail.DetailTypeEnum;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class PriceList {

    private final List<Detail> detailPricesList;

    public PriceList() {
        detailPricesList = new ArrayList<>(List.of(
                new Detail(DetailTypeEnum.BONNET, DetailSideEnum.NO_SIDE, BigDecimal.valueOf(200)),
                new Detail(DetailTypeEnum.BOOT, DetailSideEnum.NO_SIDE, BigDecimal.valueOf(180)),
                new Detail(DetailTypeEnum.ROOF, DetailSideEnum.NO_SIDE, BigDecimal.valueOf(250)),
                new Detail(DetailTypeEnum.BUMPER, DetailSideEnum.FRONT, BigDecimal.valueOf(180)),
                new Detail(DetailTypeEnum.BUMPER, DetailSideEnum.REAR, BigDecimal.valueOf(150)),
                new Detail(DetailTypeEnum.DOOR, DetailSideEnum.FRONT_LEFT, BigDecimal.valueOf(180)),
                new Detail(DetailTypeEnum.DOOR, DetailSideEnum.FRONT_RIGHT, BigDecimal.valueOf(180)),
                new Detail(DetailTypeEnum.DOOR, DetailSideEnum.REAR_LEFT, BigDecimal.valueOf(180)),
                new Detail(DetailTypeEnum.DOOR, DetailSideEnum.REAR_RIGHT, BigDecimal.valueOf(180)),
                new Detail(DetailTypeEnum.WING, DetailSideEnum.FRONT_LEFT, BigDecimal.valueOf(130)),
                new Detail(DetailTypeEnum.WING, DetailSideEnum.FRONT_RIGHT, BigDecimal.valueOf(130)),
                new Detail(DetailTypeEnum.WING, DetailSideEnum.REAR_LEFT, BigDecimal.valueOf(160)),
                new Detail(DetailTypeEnum.WING, DetailSideEnum.REAR_RIGHT, BigDecimal.valueOf(160)),
                new Detail(DetailTypeEnum.MIRROR, DetailSideEnum.LEFT, BigDecimal.valueOf(60)),
                new Detail(DetailTypeEnum.MIRROR, DetailSideEnum.RIGHT, BigDecimal.valueOf(60))
        ));


//        detailPricesList.add(bonnet);
//        detailPricesList.add(boot);
//        detailPricesList.add(roof);
//        detailPricesList.add(frontBumper);
//        detailPricesList.add(rearBumper);
//        detailPricesList.add(frontLeftDoor);
//        detailPricesList.add(frontRightDoor);
//        detailPricesList.add(rearLeftDoor);
//        detailPricesList.add(rearRightDoor);
//        detailPricesList.add(frontLeftWing);
//        detailPricesList.add(frontRightWing);
//        detailPricesList.add(rearLeftWing);
//        detailPricesList.add(rearRightWing);
//        detailPricesList.add(leftWingMirror);
//        detailPricesList.add(rightWingMirror);

        Long nextId = 1L;

        for (Detail detail : detailPricesList) {
            detail.setId(nextId);
            nextId++;
        }
    }

    public Optional<Detail> findById(Long id) {
        return detailPricesList.stream()
                .filter(detail -> detail.getId().equals(id))
                .findFirst();
    }

    public List<Detail> getDetailPricesList() {
        return detailPricesList;
    }

}
