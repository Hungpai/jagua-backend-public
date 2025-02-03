package com.hung.jagua.controller;

import com.hung.jagua.entity.Type;
import com.hung.jagua.entity.Vocabulary;
import com.hung.jagua.repository.VocabularyRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("lection")
public class VocabularyController {
    private final VocabularyRepository vocRepository;

    @GetMapping("/voc/{lection}")
    public ResponseEntity<List<Vocabulary>> findAllVocabularyByLection(@PathVariable int lection) {
        List<Vocabulary> vocs = vocRepository.findAllByLectionAndTypeOrderById(lection, Type.VOCABULARY);
        return ResponseEntity.ok(vocs);
    }

    @GetMapping("/pattern/{lection}")
    public ResponseEntity<List<Vocabulary>> findAllPatternByLection(@PathVariable int lection) {
        List<Vocabulary> patterns = vocRepository.findAllByLectionAndTypeOrderById(lection, Type.PATTERN);
        return ResponseEntity.ok(patterns);
    }
}
