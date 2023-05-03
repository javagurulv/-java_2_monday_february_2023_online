package lv.javaguru.java2.servify.domain.detail;

import java.math.BigDecimal;
import java.util.Objects;

public class Detail {

    private Long id;
    private DetailTypeEnum type;
    // Bonnet – капот.
    // Boot  – багажник.
    // Bumper  – бампер.
    // Roof - крыша.
    // Door – дверь.
    // Wing - крыло автомобиля
    // Wing mirror  – боковое зеркало.
    private DetailSideEnum side;
    private BigDecimal price;

    public Detail(DetailTypeEnum type, DetailSideEnum side, BigDecimal price) {
        this.type = type;
        this.side = side;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DetailTypeEnum getType() {
        return type;
    }

    public void setType(DetailTypeEnum type) {
        this.type = type;
    }

    public DetailSideEnum getSide() {
        return side;
    }

    public void setSide(DetailSideEnum side) {
        this.side = side;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return id +
                ". " + type + '\'' +
                ", side - " + side;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Detail detail)) return false;
        return Objects.equals(type, detail.type) && Objects.equals(getSide(), detail.getSide());
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, getSide());
    }

}