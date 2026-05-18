package com.practise.restaurant_management.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "menus")
@Getter
@Setter
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(fetch = FetchType.LAZY)
    @Column(name = "dishes_id")
    private List<Dishes> dishes;

    @OneToOne
    private Branches branch;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
