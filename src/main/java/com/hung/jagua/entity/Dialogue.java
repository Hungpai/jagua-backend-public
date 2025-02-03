package com.hung.jagua.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class Dialogue extends LectionItem{

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long diaId;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "diaId")
    private List<DialogueItem> dialogue;

    @Enumerated(EnumType.STRING)
    private Type type;

    private String title;

//    private int lection;
}
