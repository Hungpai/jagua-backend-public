package com.hung.jagua.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Vocabulary extends LectionItem{

    @Enumerated(EnumType.STRING)
    private Type type;

    @OneToMany(mappedBy = "vocabulary")
    @OrderColumn(name = "position") // Ensures order is preserved
    private List<VocabularyKanji> kanji = new ArrayList<>();

    private String word_jp;
    private String word_de;
}
