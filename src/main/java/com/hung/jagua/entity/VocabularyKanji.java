package com.hung.jagua.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class VocabularyKanji {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @ManyToOne
    @JoinColumn(name = "voc_id", nullable = false)
    @JsonIgnore
    private Vocabulary vocabulary;

    @ManyToOne
    @JoinColumn(name = "kanji_id", nullable = false)
    private Kanji kanji;

    private int position; // Optional, if you need explicit control over the order
}
