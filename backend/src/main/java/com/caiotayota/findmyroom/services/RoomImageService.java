package com.caiotayota.findmyroom.services;

import com.caiotayota.findmyroom.entities.RoomImage;
import com.caiotayota.findmyroom.repositories.RoomImageRepository;
import com.caiotayota.findmyroom.utils.ImageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
public class RoomImageService {
    private final RoomImageRepository roomImageRepository;

    @Autowired
    public RoomImageService(RoomImageRepository roomImageRepository) {
        this.roomImageRepository = roomImageRepository;
    }

    public long uploadImage(MultipartFile file) throws IOException {

        RoomImage imageData = roomImageRepository.save(RoomImage.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .imageData(ImageUtils.compressImage(file.getBytes())).build());
        if (imageData != null) {
            System.out.println("file uploaded successfully : " + file.getOriginalFilename());
            return imageData.getId();
        }
        return 0;
    }

    public byte[] downloadImage(long fileId){
        Optional<RoomImage> dbImageData = roomImageRepository.findById(fileId);
        byte[] images=ImageUtils.decompressImage(dbImageData.get().getImageData());
        return images;
    }
}
