package ru.konverdev.spring.Repository;

import org.springframework.data.repository.CrudRepository;
import ru.konverdev.spring.Domain.Tag;

public interface TagRepository extends CrudRepository<Tag, Integer> {

}