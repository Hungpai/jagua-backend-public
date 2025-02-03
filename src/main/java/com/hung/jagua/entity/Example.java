package com.hung.jagua.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class Example extends LectionItem {

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long exampleId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "question_id")
    private Vocabulary question;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "answer_id")
    private Vocabulary answer;

    @Enumerated(EnumType.STRING)
    private Type type;

//    private int lection;
}