package com.ocean.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ocean.backend.dto.CategoryCountVm;
import com.ocean.backend.entity.Category;
import com.ocean.backend.entity.enums.Status;
public interface CategoryRepository extends JpaRepository<Category, Long> {

    List<Category> findTop5ByTypeAndStatusOrderByIdAsc(String type, Status status);
    List<Category> findByTypeAndStatusOrderByNameAsc(
            String type,
            Status status
    );
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
            @Param("pStatus") Status pStatus
    );
}


