package com.ocean.backend.service.admin;

import com.ocean.backend.dto.request.CategoryRequestDTO;
import com.ocean.backend.dto.response.CategoryResponseDTO;
import com.ocean.backend.entity.Category;
import com.ocean.backend.entity.enums.Status;
import com.ocean.backend.repository.CategoryRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminCategoryService {

    private final CategoryRepository categoryRepository;

    // ── LIST (paginated) ──────────────────────────────────
    @Transactional(readOnly = true)
    public Page<CategoryResponseDTO> getAll(
            String search, String type, String status, Pageable pageable) {

        Status statusEnum = null;
        if (status != null && !status.isBlank()) {
            try { statusEnum = Status.valueOf(status.toUpperCase()); }
            catch (IllegalArgumentException ignored) {}
        }

        return categoryRepository
                .findAllWithFilters(blankToNull(search), blankToNull(type), statusEnum, pageable)
                .map(this::toDTO);
    }

    // ── LIST ALL (dropdown) ───────────────────────────────
    @Transactional(readOnly = true)
    public List<CategoryResponseDTO> getAllForDropdown() {
        return categoryRepository.findAllByOrderByNameAsc()
                .stream().map(this::toDTO).toList();
    }

    // ── DETAIL ────────────────────────────────────────────
    @Transactional(readOnly = true)
    public CategoryResponseDTO getById(Long id) {
        return toDTO(findOrThrow(id));
    }

    // ── CREATE ────────────────────────────────────────────
    @Transactional
    public CategoryResponseDTO create(CategoryRequestDTO req) {
        validateSlugTypeUnique(req.getSlug(), req.getType(), null);

        Category parent = req.getParentId() != null
                ? findOrThrow(req.getParentId()) : null;

        Category category = Category.builder()
                .name(req.getName())
                .slug(req.getSlug())
                .type(req.getType().toUpperCase())
                .parent(parent)
                .status(parseStatus(req.getStatus()))
                .build();

        return toDTO(categoryRepository.save(category));
    }

    // ── UPDATE ────────────────────────────────────────────
    @Transactional
    public CategoryResponseDTO update(Long id, CategoryRequestDTO req) {
        Category category = findOrThrow(id);
        validateSlugTypeUnique(req.getSlug(), req.getType(), id);

        Category parent = req.getParentId() != null
                ? findOrThrow(req.getParentId()) : null;

        category.setName(req.getName());
        category.setSlug(req.getSlug());
        category.setType(req.getType().toUpperCase());
        category.setParent(parent);
        category.setStatus(parseStatus(req.getStatus()));

        return toDTO(categoryRepository.save(category));
    }

    // ── DELETE ────────────────────────────────────────────
    @Transactional
    public void delete(Long id) {
        Category category = findOrThrow(id);
        if (!category.getProducts().isEmpty())
            throw new IllegalStateException("Không thể xóa danh mục đang có sản phẩm!");
        if (!category.getChildren().isEmpty())
            throw new IllegalStateException("Không thể xóa danh mục đang có danh mục con!");
        categoryRepository.deleteById(id);
    }

    // ── PRIVATE HELPERS ───────────────────────────────────
    private Category findOrThrow(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Danh mục không tồn tại: " + id));
    }

    private void validateSlugTypeUnique(String slug, String type, Long excludeId) {
        boolean exists = excludeId == null
                ? categoryRepository.existsBySlugAndType(slug, type.toUpperCase())
                : categoryRepository.existsBySlugAndTypeAndIdNot(slug, type.toUpperCase(), excludeId);
        if (exists) throw new IllegalArgumentException("Slug + type đã tồn tại: " + slug);
    }

    private Status parseStatus(String s) {
        try { return Status.valueOf(s.toUpperCase()); }
        catch (Exception e) { return Status.ACTIVE; }
    }

    private String blankToNull(String s) {
        return (s == null || s.isBlank()) ? null : s;
    }

    private CategoryResponseDTO toDTO(Category c) {
        return CategoryResponseDTO.builder()
                .id(c.getId())
                .name(c.getName())
                .slug(c.getSlug())
                .type(c.getType())
                .status(c.getStatus() != null ? c.getStatus().name() : null)
                .parentId(c.getParent() != null ? c.getParent().getId() : null)
                .parentName(c.getParent() != null ? c.getParent().getName() : null)
                .createdAt(c.getCreatedAt())
                .updatedAt(c.getUpdatedAt())
                .build();
    }
}