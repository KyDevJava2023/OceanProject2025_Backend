package com.ocean.backend.controller.admin;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ocean.backend.dto.request.CategoryRequestDTO;
import com.ocean.backend.dto.response.CategoryResponseDTO;
import com.ocean.backend.service.admin.AdminCategoryService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/admin/categories")
@RequiredArgsConstructor
public class AdminCategoryController {

    private final AdminCategoryService categoryService;

    // GET /api/admin/categories?page=0&size=10&search=&type=&status=
    @GetMapping
    public ResponseEntity<?> getAll(
            @RequestParam(defaultValue = "")   String search,
            @RequestParam(defaultValue = "")   String type,
            @RequestParam(defaultValue = "")   String status,
            @RequestParam(defaultValue = "0")  int page,
            @RequestParam(defaultValue = "100") int size,
            @RequestParam(defaultValue = "name,asc") String sort) {

        // size=100 mặc định để FE dùng làm dropdown
        // Nếu page/size truyền vào thì dùng pagination bình thường
        String[] sortParts = sort.split(",");
        String sortField = sortParts[0];
        Sort.Direction dir = sortParts.length > 1 && sortParts[1].equalsIgnoreCase("desc")
                ? Sort.Direction.DESC : Sort.Direction.ASC;

        Pageable pageable = PageRequest.of(page, size, Sort.by(dir, sortField));
        Page<CategoryResponseDTO> result = categoryService.getAll(search, type, status, pageable);

        // Nếu FE gọi không có pagination param (size=100, page=0) trả về Page object
        return ResponseEntity.ok(result);
    }

    // GET /api/admin/categories/all  — danh sách đầy đủ cho dropdown
    @GetMapping("/all")
    public ResponseEntity<List<CategoryResponseDTO>> getAllForDropdown() {
        return ResponseEntity.ok(categoryService.getAllForDropdown());
    }

    // GET /api/admin/categories/{id}
    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(categoryService.getById(id));
    }

    // POST /api/admin/categories
    @PostMapping
    public ResponseEntity<CategoryResponseDTO> create(@Valid @RequestBody CategoryRequestDTO req) {
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.create(req));
    }

    // PUT /api/admin/categories/{id}
    @PutMapping("/{id}")
    public ResponseEntity<CategoryResponseDTO> update(
            @PathVariable Long id,
            @Valid @RequestBody CategoryRequestDTO req) {
        return ResponseEntity.ok(categoryService.update(id, req));
    }

    // DELETE /api/admin/categories/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        categoryService.delete(id);
        return ResponseEntity.noContent().build();
    }
}