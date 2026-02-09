package com.ocean.backend.repository;

import java.math.BigDecimal;

import org.springframework.data.jpa.domain.Specification;

import com.ocean.backend.entity.Product;
import com.ocean.backend.entity.enums.Status;

public class ProductSpecs {

    public static Specification<Product> isActive() {
        return (root, query, cb) -> cb.equal(root.get("status"), Status.ACTIVE);
    }

    public static Specification<Product> notDeleted() {
        return (root, query, cb) -> cb.isNull(root.get("deletedAt"));
    }

    public static Specification<Product> categorySlug(String slug) {
        return (root, query, cb) -> {
            if (slug == null || slug.trim().isEmpty())
                return cb.conjunction();
            var c = root.join("category"); // join category
            return cb.equal(c.get("slug"), slug.trim());
        };
    }

    public static Specification<Product> keyword(String q) {
        return (root, query, cb) -> {
            if (q == null || q.trim().isEmpty())
                return cb.conjunction();

            String kw = "%" + q.trim().toLowerCase() + "%";

            // name/slug là VARCHAR -> lower OK
            var p1 = cb.like(cb.lower(root.get("name")), kw);
            var p2 = cb.like(cb.lower(root.get("slug")), kw);

            // shortDescription là TEXT/CLOB -> không lower(), dùng like trực tiếp
            // (MySQL collation utf8mb4_unicode_ci thường đã case-insensitive)
            var p3 = cb.like(root.get("shortDescription"), "%" + q.trim() + "%");

            return cb.or(p1, p2, p3);
        };

    }

    public static Specification<Product> maxPrice(BigDecimal max) {
        return (root, query, cb) -> {
            if (max == null)
                return cb.conjunction();

            // nếu lọc giá thì loại những product price null
            return cb.and(
                    cb.isNotNull(root.get("price")),
                    cb.lessThanOrEqualTo(root.get("price"), max));
        };
    }
}
