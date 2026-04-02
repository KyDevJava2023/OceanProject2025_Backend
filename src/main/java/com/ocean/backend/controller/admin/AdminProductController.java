package com.ocean.backend.controller.admin;

import java.util.List;
import java.util.Map;

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

import com.ocean.backend.dto.request.ProductRequestDTO;
import com.ocean.backend.dto.response.ProductResponseDTO;
import com.ocean.backend.service.admin.AdminProductService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/admin/products")
@RequiredArgsConstructor
public class AdminProductController {

    private final AdminProductService productService;

    // GET /api/admin/products?page=0&size=10&sort=name,asc&search=&status=&categoryId=&isBestSeller=
    @GetMapping
    public ResponseEntity<Page<ProductResponseDTO>> getAll(
            @RequestParam(defaultValue = "")  String search,
            @RequestParam(defaultValue = "")  String status,
            @RequestParam(required = false)   Long categoryId,
            @RequestParam(required = false)   Boolean isBestSeller,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id,asc") String sort) {

        String[] sortParts = sort.split(",");
        String sortField = sortParts[0];
        Sort.Direction dir = sortParts.length > 1 && sortParts[1].equalsIgnoreCase("desc")
                ? Sort.Direction.DESC : Sort.Direction.ASC;

        Pageable pageable = PageRequest.of(page, size, Sort.by(dir, sortField));
        return ResponseEntity.ok(productService.getAll(search, status, categoryId, isBestSeller, pageable));
    }

    // GET /api/admin/products/{id}
    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(productService.getById(id));
    }

    // POST /api/admin/products
    @PostMapping
    public ResponseEntity<ProductResponseDTO> create(@Valid @RequestBody ProductRequestDTO req) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.create(req));
    }

    // PUT /api/admin/products/{id}
    @PutMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> update(
            @PathVariable Long id,
            @Valid @RequestBody ProductRequestDTO req) {
        return ResponseEntity.ok(productService.update(id, req));
    }

    // DELETE /api/admin/products/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // DELETE /api/admin/products/bulk-delete
    @DeleteMapping("/bulk-delete")
    public ResponseEntity<Map<String, Object>> bulkDelete(@RequestBody List<Long> ids) {
        productService.bulkDelete(ids);
        return ResponseEntity.ok(Map.of(
                "message", "Đã xóa " + ids.size() + " sản phẩm",
                "deleted", ids.size()
        ));
    }
}