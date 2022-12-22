package com.caiotayota.findmyroom.controllers;

import com.caiotayota.findmyroom.entities.RoomImage;
import com.caiotayota.findmyroom.services.RoomImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@CrossOrigin(origins = {"http://localhost:5173", "https://findmyroom.ie"})
@RestController
@RequestMapping("/api/img")
public class RoomImageController {

    private final RoomImageService roomImageService;

    @Autowired
    public RoomImageController(RoomImageService roomImageService) {
        this.roomImageService = roomImageService;
    }

    @PostMapping("/upload")
    public ResponseEntity<?> uploadImage(@RequestParam("image") MultipartFile file) throws IOException {

        long roomImageId = roomImageService.uploadImage(file);
        if (roomImageId > 0) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(roomImageId);
        }

        return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE).body("Upload failed!");

    }

    @GetMapping("/{fileId}")
    public ResponseEntity<?> downloadImage(@PathVariable long fileId){
        byte[] imageData = roomImageService.downloadImage(fileId);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png"))
                .body(imageData);

    }
}
