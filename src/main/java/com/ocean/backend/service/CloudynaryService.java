package com.ocean.backend.service;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

@Service
public class CloudynaryService {

    private final Cloudinary cloudinary;

    public CloudynaryService(
            @Value("${cloudinary.cloud_name}") String cloudName,
            @Value("${cloudinary.api_key}")    String apiKey,
            @Value("${cloudinary.api_secret}") String apiSecret) {

        this.cloudinary = new Cloudinary(ObjectUtils.asMap(
            "cloud_name", cloudName,
            "api_key",    apiKey,
            "api_secret", apiSecret
        ));
    }

    /**
     * Upload thumbnail bài viết → folder "aztax/thumbnails"
     */
    public UploadResult uploadThumbnail(MultipartFile file) {
        return upload(file, "aztax/thumbnails");
    }

    /**
     * Upload ảnh trong CKEditor → folder "aztax/content"
     */
    public UploadResult uploadEditorImage(MultipartFile file) {
        return upload(file, "aztax/content");
    }

    private UploadResult upload(MultipartFile file, String folder) {
        try {
            String publicId = folder + "/" + UUID.randomUUID();

            @SuppressWarnings("unchecked")
            Map<String, Object> result = cloudinary.uploader().upload(
                file.getBytes(),
                ObjectUtils.asMap(
                    "public_id",      publicId,
                    "folder",         folder,
                    "resource_type",  "image",
                    "overwrite",      true
                )
            );

            String url      = (String) result.get("secure_url");
            String retPublicId = (String) result.get("public_id");
            return new UploadResult(url, retPublicId);

        } catch (IOException e) {
            throw new RuntimeException("Upload Cloudinary thất bại: " + e.getMessage(), e);
        }
    }

    /**
     * Xóa ảnh khỏi Cloudinary theo publicId
     */
    public void delete(String publicId) {
        try {
            cloudinary.uploader().destroy(publicId, ObjectUtils.emptyMap());
        } catch (IOException e) {
            // Log nhưng không throw — xóa ảnh cũ không critical
            System.err.println("Cloudinary delete failed: " + e.getMessage());
        }
    }

    public record UploadResult(String url, String publicId) {}
}
