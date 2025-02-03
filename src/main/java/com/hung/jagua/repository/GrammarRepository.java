package com.hung.jagua.repository;

import com.hung.jagua.entity.Grammar;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface GrammarRepository extends CrudRepository<Grammar, Long> {
    List<Grammar> findAllByLectionOrderById(int lection);
}
