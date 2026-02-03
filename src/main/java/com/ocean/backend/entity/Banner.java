package com.ocean.backend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "banners")
@Getter @Setter
public class Banner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(name = "image_url")
    private String imageUrl;

    private String link;

    private String position;

    private String status;

    @Column(name = "order_index")
    private Integer orderIndex;
}
