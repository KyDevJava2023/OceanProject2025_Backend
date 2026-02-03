package com.ocean.backend.entity;

import com.ocean.backend.entity.enums.Status;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "products", indexes = {
        @Index(name = "idx_products_slug", columnList = "slug"),
        @Index(name = "idx_products_category_id", columnList = "category_id")
})
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 255)
    private String name;

    @Column(nullable = false, length = 255, unique = true)
    private String slug;

    @Lob
    @Column(name = "short_description")
    private String shortDescription;

    @Lob
    @Column(name = "content")
    private String content; // CKEditor HTML

    @Column(precision = 15, scale = 2)
    private BigDecimal price;

    @Column(length = 500)
    private String thumbnail; // Cloudinary URL
    @Column(name = "is_bestseller")
    private Boolean isBestSeller = false;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private Status status = Status.ACTIVE;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    // === NEW: many-to-one ===
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "category_id", foreignKey = @ForeignKey(name = "fk_products_category"))
    private Category category;

    @PrePersist
    void prePersist() {
        if (createdAt == null)
            createdAt = LocalDateTime.now();
        if (updatedAt == null)
            updatedAt = LocalDateTime.now();
        if (status == null)
            status = Status.ACTIVE;
    }

    @PreUpdate
    void preUpdate() {
        updatedAt = LocalDateTime.now();
    }

    // helper soft delete
    public void softDelete() {
        this.deletedAt = LocalDateTime.now();
        this.status = Status.INACTIVE;
    }
}
