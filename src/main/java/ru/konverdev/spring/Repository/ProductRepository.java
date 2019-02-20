package ru.konverdev.spring.Repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;
import ru.konverdev.spring.Domain.Product;

public interface ProductRepository extends CrudRepository<Product, Integer> {
    @Transactional
    @Modifying
    @Query(value = "UPDATE product SET title = ?2, tag = ?3, img_path = ?4, price = ?5, unit = ?6 where id = ?1", nativeQuery = true)
    void UpdateProduct(Integer id, String title, Integer tag, String img_path, double price, String unit);

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO product (title, price, tag, img_path, unit) VALUES(?1, ?2, ?3, ?4, ?5)", nativeQuery = true)
    void AddProduct(String title, double price, Integer tag, String img_path, String unit);

    @Transactional
    @Modifying
    @Query(value = "delete from product where id = ?1", nativeQuery = true)
    int DeleteProduct(Integer id);
}