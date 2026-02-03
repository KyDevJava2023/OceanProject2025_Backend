package com.ocean.backend.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.ocean.backend.entity.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ocean.backend.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findTop8ByCategory_IdAndDeletedAtIsNullAndStatusOrderByIdDesc(
            Long categoryId, Status status
    );
     List<Product> findTop12ByCategory_SlugAndCategory_TypeAndDeletedAtIsNullAndStatusOrderByIdDesc(
            String categorySlug, String categoryType, Status status
    );
     List<Product> findTop6ByIsBestSellerTrueAndStatusAndDeletedAtIsNullOrderByUpdatedAtDesc(
            Status status
    );
    
    @Query("""
        select p from Product p
        join fetch p.category c
        where p.deletedAt is null
          and p.status = :status
          and (:cateSlug is null or c.slug = :cateSlug)
        order by p.createdAt desc
    """)
    Page<Product> findForList(
            @Param("cateSlug") String cateSlug,
            @Param("status") Status status,
            Pageable pageable
    );
}

