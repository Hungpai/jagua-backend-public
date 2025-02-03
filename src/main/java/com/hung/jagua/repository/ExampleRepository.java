package com.hung.jagua.repository;

import com.hung.jagua.entity.Example;
import com.hung.jagua.entity.Type;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ExampleRepository extends CrudRepository<Example, Long> {
    List<Example> findAllByLectionAndTypeOrderById(int lection, Type type);
}
