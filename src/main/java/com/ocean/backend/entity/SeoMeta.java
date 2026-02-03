package com.ocean.backend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "seo_meta")
@Getter @Setter
public class SeoMeta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "entity_type")
    private String entityType; // PAGE | PRODUCT | POST | SERVICE | CATEGORY

    @Column(name = "entity_id")
    private Long entityId;

    private String metaTitle;
    private String metaDescription;
    private String metaKeywords;
    private String ogImage;
}
