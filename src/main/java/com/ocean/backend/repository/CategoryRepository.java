package com.ocean.backend.repository;

import com.ocean.backend.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import com.ocean.backend.entity.enums.Status;
import java.util.List;
import java.util.Optional;
public interface CategoryRepository extends JpaRepository<Category, Long> {

    List<Category> findTop5ByTypeAndStatusOrderByIdAsc(String type, Status status);
}


