package com.hung.jagua.controller;

import com.hung.jagua.entity.Dialogue;
import com.hung.jagua.entity.Grammar;
import com.hung.jagua.repository.GrammarRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/lection")
@AllArgsConstructor
public class GrammarController {

    private final GrammarRepository grammarRepository;

    @GetMapping("/grammar/{lection}")
    public ResponseEntity<List<Grammar>> findByLection(@PathVariable int lection) {
        List<Grammar> gr = grammarRepository.findAllByLectionOrderById(lection);
        return ResponseEntity.ok(gr);
    }
}
