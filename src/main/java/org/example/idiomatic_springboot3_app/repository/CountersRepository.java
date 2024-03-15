package org.example.idiomatic_springboot3_app.repository;

import org.example.idiomatic_springboot3_app.model.Counter;
import org.springframework.data.repository.ListCrudRepository;

public interface CountersRepository extends ListCrudRepository<Counter, String> {
}
