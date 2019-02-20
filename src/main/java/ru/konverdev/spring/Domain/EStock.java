package ru.konverdev.spring.Domain;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Embeddable
public class EStock implements Serializable {
    @NotNull
    private Integer product;
    @NotNull
    private Integer wagon;

    public Integer getProduct() {
        return product;
    }

    public void setProduct(Integer product) {
        this.product = product;
    }

    public Integer getWagon() {
        return wagon;
    }

    public void setWagon(Integer wagon) {
        this.wagon = wagon;
    }
}