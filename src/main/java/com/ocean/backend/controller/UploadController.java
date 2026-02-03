package com.ocean.backend.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ocean.backend.service.UploadService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/admin/uploads")
@RequiredArgsConstructor
public class UploadController {
    
    private final UploadService uploadService;
    
    @PostMapping
    public ResponseEntity<Map<String, String>> upload(
        @RequestParam("file") MultipartFile file
    ) {
        try {
            String url = uploadService.uploadImage(file);
            
            Map<String, String> response = new HashMap<>();
            response.put("url", url);
            response.put("message", "Upload successful");
            
            return ResponseEntity.ok(response);
            
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Upload failed: " + e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }
    
    @PostMapping("/multiple")
    public ResponseEntity<Map<String, Object>> uploadMultiple(
        @RequestParam("files") MultipartFile[] files
    ) {
        try {
            java.util.List<String> urls = new java.util.ArrayList<>();
            
            for (MultipartFile file : files) {
                String url = uploadService.uploadImage(file);
                urls.add(url);
            }
            
            Map<String, Object> response = new HashMap<>();
            response.put("urls", urls);
            response.put("count", urls.size());
            response.put("message", "Upload successful");
            
            return ResponseEntity.ok(response);
            
        } catch (Exception e) {
            Map<String, Object> error = new HashMap<>();
            error.put("error", "Upload failed: " + e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }
}