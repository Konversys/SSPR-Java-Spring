package ru.konverdev.spring.Repository.Special;

public class RProduct {
    private Integer id;
    private String title;
    private double price;
    private String tag;
    private String unit;
    private String img_path;

    public RProduct(Integer id, String title, double price, String tag, String unit, String img_path) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.tag = tag;
        this.unit = unit;
        this.img_path = img_path;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getImg_path() {
        return img_path;
    }

    public void setImg_path(String img_path) {
        this.img_path = img_path;
    }
}
