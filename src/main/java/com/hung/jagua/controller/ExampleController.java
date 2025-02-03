package com.hung.jagua.controller;

import com.hung.jagua.entity.Example;
import com.hung.jagua.entity.Type;
import com.hung.jagua.repository.ExampleRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/lection")
public class ExampleController {
    private final ExampleRepository exRepository;

    @GetMapping("/example/{lection}")
    public ResponseEntity<List<Example>> findAllByLection(@PathVariable int lection) {
        List<Example> examples = exRepository.findAllByLectionAndTypeOrderById(lection, Type.EXAMPLE);
        return ResponseEntity.ok(examples);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Example> findById(@PathVariable Long id) {
        Example ex = exRepository.findById(id).orElse(null);
        if (ex != null) {
            return ResponseEntity.ok(ex);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}


