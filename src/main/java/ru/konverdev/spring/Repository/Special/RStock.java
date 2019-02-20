package ru.konverdev.spring.Repository.Special;

import ru.konverdev.spring.Domain.EStock;

public class RStock {
    private Integer wagon_id;
    private Integer product_id;
    private String product;
    private double price;
    private Integer count;
    private Integer selled;
    private double total;

    public RStock(Integer wagon_id, Integer product_id, String product, double price, Integer count, Integer selled, double total) {
        this.wagon_id = wagon_id;
        this.product_id = product_id;
        this.product = product;
        this.price = price;
        this.count = count;
        this.selled = selled;
        this.total = total;
    }

    public Integer getWagon_id() {
        return wagon_id;
    }

    public void setWagon_id(Integer wagon_id) {
        this.wagon_id = wagon_id;
    }

    public Integer getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Integer product_id) {
        this.product_id = product_id;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
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

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
