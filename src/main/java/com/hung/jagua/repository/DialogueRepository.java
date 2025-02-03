package com.hung.jagua.repository;

import com.hung.jagua.entity.Dialogue;
import com.hung.jagua.entity.Type;
import org.springframework.data.repository.CrudRepository;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface DialogueRepository extends CrudRepository<Dialogue, Long> {
    Optional<Dialogue> findByLectionAndTypeOrderById(int lection, Type type);
}
