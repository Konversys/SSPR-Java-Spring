package ru.konverdev.spring.Domain;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class Stock {
    @EmbeddedId
    private EStock eStock;
    private Integer count;
    private Integer selled;

    public EStock geteStock() {
        return eStock;
    }

    public void seteStock(EStock eStock) {
        this.eStock = eStock;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getSelled() {
        return selled;
    }

    public void setSelled(Integer selled) {
        this.selled = selled;
    }
}
