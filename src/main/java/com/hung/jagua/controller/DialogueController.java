package com.hung.jagua.controller;

import com.hung.jagua.entity.Dialogue;
import com.hung.jagua.entity.Type;
import com.hung.jagua.repository.DialogueRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/lection")
public class DialogueController {

    private final DialogueRepository diaRepository;

    @GetMapping("/dialogue/{lection}")
    public ResponseEntity<Dialogue> findByLection(@PathVariable int lection) {
        Dialogue dialogue = diaRepository.findByLectionAndTypeOrderById(lection, Type.DIALOGUE).orElse(null);
        if (dialogue != null) {
            return ResponseEntity.ok(dialogue);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
