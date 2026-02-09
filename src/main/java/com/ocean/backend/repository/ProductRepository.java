package com.ocean.backend.repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ocean.backend.entity.Product;
import com.ocean.backend.entity.enums.Status;

public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {
  // featured = bestseller
  @EntityGraph(attributePaths = { "category" })
  List<Product> findTop3ByIsBestSellerTrueAndStatusAndDeletedAtIsNullOrderByUpdatedAtDesc(Status status);

  @Query("""
          select coalesce(max(p.price), 0)
          from Product p
          where p.status = :status
            and p.deletedAt is null
            and p.price is not null
      """)
  BigDecimal findMaxPrice(@Param("status") Status status);

  @Override
  @EntityGraph(attributePaths = { "category" })
  Page<Product> findAll(Specification<Product> spec, Pageable pageable);

  List<Product> findTop8ByCategory_IdAndDeletedAtIsNullAndStatusOrderByIdDesc(
      Long categoryId, Status status);

  List<Product> findTop12ByCategory_SlugAndCategory_TypeAndDeletedAtIsNullAndStatusOrderByIdDesc(
      String categorySlug, String categoryType, Status status);

  @EntityGraph(attributePaths = { "category" })
  List<Product> findTop6ByIsBestSellerTrueAndStatusAndDeletedAtIsNullOrderByUpdatedAtDesc(
      Status status);

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
      Pageable pageable);

  @EntityGraph(attributePaths = { "category" })
  Optional<Product> findBySlugAndStatusAndDeletedAtIsNull(String slug, Status status);

  @EntityGraph(attributePaths = { "category" })
  List<Product> findTop8ByCategory_IdAndIdNotAndStatusAndDeletedAtIsNullOrderByCreatedAtDesc(
      Long categoryId, Long excludeId, Status status);
}
