package com.hung.jagua.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public abstract class LectionItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;

    protected int lection;
}
