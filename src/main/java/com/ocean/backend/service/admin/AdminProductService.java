package com.ocean.backend.service.admin;

import com.ocean.backend.dto.request.ProductRequestDTO;
import com.ocean.backend.dto.response.ProductResponseDTO;
import com.ocean.backend.entity.Category;
import com.ocean.backend.entity.Product;
import com.ocean.backend.entity.enums.Status;
import com.ocean.backend.repository.CategoryRepository;
import com.ocean.backend.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    // ── LIST ──────────────────────────────────────────────
    @Transactional(readOnly = true)
    public Page<ProductResponseDTO> getAll(
            String search, String status, Long categoryId,
            Boolean isBestSeller, Pageable pageable) {

        Status statusEnum = null;
        if (status != null && !status.isBlank()) {
            try { statusEnum = Status.valueOf(status.toUpperCase()); }
            catch (IllegalArgumentException ignored) {}
        }

        return productRepository
                .findAllWithFilters(
                        blankToNull(search), statusEnum, categoryId, isBestSeller, pageable)
                .map(this::toDTO);
    }

    // ── DETAIL ────────────────────────────────────────────
    @Transactional(readOnly = true)
    public ProductResponseDTO getById(Long id) {
        return toDTO(findOrThrow(id));
    }

    // ── CREATE ────────────────────────────────────────────
    @Transactional
    public ProductResponseDTO create(ProductRequestDTO req) {
        validateSlugUnique(req.getSlug(), null);
        Category category = findCategoryOrThrow(req.getCategoryId());

        Product product = Product.builder()
                .name(req.getName())
                .slug(req.getSlug())
                .shortDescription(req.getShortDescription())
                .content(req.getContent())
                .price(req.getPrice())
                .thumbnail(req.getThumbnail())
                .isBestSeller(Boolean.TRUE.equals(req.getIsBestSeller()))
                .status(parseStatus(req.getStatus()))
                .category(category)
                .build();

        return toDTO(productRepository.save(product));
    }

    // ── UPDATE ────────────────────────────────────────────
    @Transactional
    public ProductResponseDTO update(Long id, ProductRequestDTO req) {
        Product product = findOrThrow(id);
        validateSlugUnique(req.getSlug(), id);
        Category category = findCategoryOrThrow(req.getCategoryId());

        product.setName(req.getName());
        product.setSlug(req.getSlug());
        product.setShortDescription(req.getShortDescription());
        product.setContent(req.getContent());
        product.setPrice(req.getPrice());
        product.setThumbnail(req.getThumbnail());
        product.setIsBestSeller(Boolean.TRUE.equals(req.getIsBestSeller()));
        product.setStatus(parseStatus(req.getStatus()));
        product.setCategory(category);

        return toDTO(productRepository.save(product));
    }

    // ── DELETE ────────────────────────────────────────────
    @Transactional
    public void delete(Long id) {
        if (!productRepository.existsById(id))
            throw new EntityNotFoundException("Sản phẩm không tồn tại: " + id);
        productRepository.deleteById(id);
    }

    // ── BULK DELETE ───────────────────────────────────────
    @Transactional
    public void bulkDelete(List<Long> ids) {
        productRepository.deleteAllByIdIn(ids);
    }

    // ── PRIVATE HELPERS ───────────────────────────────────
    private Product findOrThrow(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Sản phẩm không tồn tại: " + id));
    }

    private Category findCategoryOrThrow(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Danh mục không tồn tại: " + id));
    }

    private void validateSlugUnique(String slug, Long excludeId) {
        boolean exists = excludeId == null
                ? productRepository.existsBySlug(slug)
                : productRepository.existsBySlugAndIdNot(slug, excludeId);
        if (exists) throw new IllegalArgumentException("Slug đã tồn tại: " + slug);
    }

    private Status parseStatus(String s) {
        try { return Status.valueOf(s.toUpperCase()); }
        catch (Exception e) { return Status.ACTIVE; }
    }

    private String blankToNull(String s) {
        return (s == null || s.isBlank()) ? null : s;
    }

    private ProductResponseDTO toDTO(Product p) {
        return ProductResponseDTO.builder()
                .id(p.getId())
                .name(p.getName())
                .slug(p.getSlug())
                .shortDescription(p.getShortDescription())
                .content(p.getContent())
                .price(p.getPrice())
                .thumbnail(p.getThumbnail())
                .isBestSeller(p.getIsBestSeller())
                .status(p.getStatus() != null ? p.getStatus().name() : null)
                .categoryId(p.getCategory() != null ? p.getCategory().getId() : null)
                .categoryName(p.getCategory() != null ? p.getCategory().getName() : null)
                .createdAt(p.getCreatedAt())
                .updatedAt(p.getUpdatedAt())
                .build();
    }
}