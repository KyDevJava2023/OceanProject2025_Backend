package com.ocean.backend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "pages")
@Getter @Setter
public class Page {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(unique = true)
    private String slug;

    @Lob
    private String content;

    private String status;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
