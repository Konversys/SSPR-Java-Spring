package ru.konverdev.spring.Repository.Special;

import ru.konverdev.spring.Domain.Product;
import ru.konverdev.spring.Domain.Stock;
import ru.konverdev.spring.Domain.Tag;

import java.util.ArrayList;

public class Utils {
    public static Iterable<RStock> GetRStock(Iterable<Stock> stock, Iterable<Product> products) {
        Iterable<RStock> result = new ArrayList<>();
        for (Stock item : stock) {
            Integer wagon_id = item.geteStock().getWagon();
            Integer product_id = item.geteStock().getProduct();
            double price = -1;
            String product = null;
            for (Product f : products) {
                if (f.getId() == product_id) {
                    product = f.getTitle();
                    price = f.getPrice();
                    break;
                }
            }
            Integer count = item.getCount();
            Integer selled = item.getSelled();
            ((ArrayList<RStock>) result).add(new RStock(wagon_id, product_id, product, price, count, count-selled, price * (count - selled)));
        }
        return result;
    }

    public static Iterable<RProduct> GetRProduct(Iterable<Product> products, Iterable<Tag> tags) {
        Iterable<RProduct> result = new ArrayList<>();
        for (Product item : products) {
            Integer id = item.getId();
            String title = item.getTitle();
            double price = item.getPrice();
            String unit = item.getUnit();
            String img_path = item.getImg_path();
            String tag = null;
            for (Tag f : tags) {
                if (f.getId() == item.getTag()) {
                    tag = f.getTitle();
                    break;
                }
            }
            ((ArrayList<RProduct>) result).add(new RProduct(id, title, price, tag, unit, img_path));
        }
        return result;
    }
}
