package com.ocean.backend.controller.admin;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import com.ocean.backend.service.CloudynaryService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/admin/uploads")
@RequiredArgsConstructor
public class AdminUploadController {

    private final CloudynaryService cloudinaryService;

    /**
     * Upload thumbnail bài viết
     * POST /admin/uploads/thumbnail
     * Response: { "url": "...", "publicId": "..." }
     */
    @PostMapping("/thumbnail")
    public Map<String, Object> uploadThumbnail(@RequestParam("file") MultipartFile file) {
        validate(file);
        var result = cloudinaryService.uploadThumbnail(file);
        return Map.of("url", result.url(), "publicId", result.publicId());
    }

    /**
     * Upload ảnh trong CKEditor
     * POST /admin/uploads/ckeditor
     * Response: { "url": "..." }  ← CKEditor custom adapter cần "url"
     */
    @PostMapping("/ckeditor")
    public Map<String, Object> uploadCkeditor(@RequestParam("upload") MultipartFile upload) {
        validate(upload);
        var result = cloudinaryService.uploadEditorImage(upload);
        return Map.of("url", result.url(), "publicId", result.publicId());
    }

    /* ─── Helper ─── */
    private void validate(MultipartFile file) {
        if (file == null || file.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "File trống");
        }
        if (file.getSize() > 10 * 1024 * 1024) {
            throw new ResponseStatusException(HttpStatus.PAYLOAD_TOO_LARGE, "File quá lớn. Tối đa 10MB.");
        }
        String ct = file.getContentType();
        if (ct == null || !ct.startsWith("image/")) {
            throw new ResponseStatusException(HttpStatus.UNSUPPORTED_MEDIA_TYPE, "Chỉ cho phép file ảnh");
        }
    }
}