package ru.konverdev.spring.Repository;

import org.springframework.data.repository.CrudRepository;
import ru.konverdev.spring.Domain.Wagon;

public interface WagonRepository extends CrudRepository<Wagon, Integer> {

}