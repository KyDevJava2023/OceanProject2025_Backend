package com.ocean.backend.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ocean.backend.dto.CategoryCountVm;
import com.ocean.backend.entity.Category;
import com.ocean.backend.entity.enums.Status;

public interface CategoryRepository extends JpaRepository<Category, Long> {
       @Query("""
    SELECT DISTINCT c
    FROM Category c
    LEFT JOIN FETCH c.children
    WHERE c.parent IS NULL
      AND c.type = :type
      AND c.status = 'ACTIVE'
    ORDER BY c.name
""")
List<Category> findTree(@Param("type") String type);
        List<Category> findTop4ByTypeAndStatusOrderByIdAsc(String type, Status status);

        List<Category> findByTypeAndStatusOrderByNameAsc(
                        String type,
                        Status status);

        @Query("""
                            select new com.ocean.backend.dto.CategoryCountVm(
                                c.id, c.name, c.slug,
                                count(p.id)
                            )
                            from Category c
                            left join Product p
                                   on p.category = c
                                  and p.status = :pStatus
                                  and p.deletedAt is null
                            where c.type = 'PRODUCT'
                              and c.status = :cStatus
                            group by c.id, c.name, c.slug
                            order by c.name asc
                        """)
        List<CategoryCountVm> findProductCategoryCounts(
                        @Param("cStatus") Status cStatus,
                        @Param("pStatus") Status pStatus);

        boolean existsBySlugAndType(String slug, String type);

        boolean existsBySlugAndTypeAndIdNot(String slug, String type, Long id);

        List<Category> findAllByOrderByNameAsc();

        @Query("""
                            SELECT c FROM Category c
                            LEFT JOIN FETCH c.parent
                            WHERE (:search IS NULL OR LOWER(c.name) LIKE LOWER(CONCAT('%', :search, '%')))
                              AND (:type IS NULL OR c.type = :type)
                              AND (:status IS NULL OR c.status = :status)
                        """)
        Page<Category> findAllWithFilters(
                        @Param("search") String search,
                        @Param("type") String type,
                        @Param("status") Status status,
                        Pageable pageable);
}
