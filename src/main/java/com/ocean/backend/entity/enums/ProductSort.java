package com.ocean.backend.entity.enums;

import org.springframework.data.domain.Sort;

public enum ProductSort {
    new_ {
        public Sort sort() {
            return Sort.by(
                    Sort.Order.desc("createdAt"),
                    Sort.Order.desc("id")
            );
        }
    },
    old_ {
        public Sort sort() {
            return Sort.by(
                    Sort.Order.asc("createdAt"),
                    Sort.Order.asc("id")
            );
        }
    },
    price_asc {
        public Sort sort() {
            return Sort.by(
                    Sort.Order.asc("price"),
                    Sort.Order.desc("id")
            );
        }
    },
    price_desc {
        public Sort sort() {
            return Sort.by(
                    Sort.Order.desc("price"),
                    Sort.Order.desc("id")
            );
        }
    };

    public abstract Sort sort();

    public static ProductSort from(String s) {
        if (s == null) return new_;
        return switch (s) {
            case "old" -> old_;
            case "price_asc" -> price_asc;
            case "price_desc" -> price_desc;
            default -> new_;
        };
    }
}
