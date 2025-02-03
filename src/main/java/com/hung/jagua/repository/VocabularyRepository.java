package com.hung.jagua.repository;

import com.hung.jagua.entity.Type;
import com.hung.jagua.entity.Vocabulary;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VocabularyRepository extends CrudRepository<Vocabulary, Long> {
    List<Vocabulary> findAllByLectionAndTypeOrderById(int lection, Type type);
}
