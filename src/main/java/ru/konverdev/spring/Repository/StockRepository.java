package ru.konverdev.spring.Repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;
import ru.konverdev.spring.Domain.Stock;

import java.util.List;

public interface StockRepository extends CrudRepository<Stock, Integer> {
    @Query(value = "select * from stock where wagon = ?1", nativeQuery = true)
    List<Stock> findAllByWagon(Integer id);

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO stock(wagon, product, count, selled VALUES(?1, ?2, ?3, 0) ON DUPLICATE KEY UPDATE count = count + ?3",
            nativeQuery = true)
    void SaveWagonProduct(Integer wagon, Integer product, Integer count);

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO stock(wagon, product, count, selled) VALUES(?1, ?2, ?3, 0) ON DUPLICATE KEY UPDATE count = count + ?3",
            nativeQuery = true)
    void AddProductToWagon(Integer wagon, Integer product, Integer count);

    @Transactional
    @Modifying
    @Query(value = "UPDATE stock SET selled = selled + ?3 where wagon = ?1 and product = ?2",
            nativeQuery = true)
    void UpdateSelled(Integer wagon, Integer product, Integer selled);

    @Transactional
    @Modifying
    @Query(value = "delete from stock where wagon = ?1", nativeQuery = true)
    void ClearStock(Integer wagon);
}