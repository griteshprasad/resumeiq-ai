package com.resumeiq.resume.service.storage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.resumeiq.common.config.StorageProperties;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LocalStorageService implements StorageService {

    private final StorageProperties storageProperties;

    @Override
    public StoredFile store(MultipartFile file) {

        try {

            Path uploadPath = Paths.get(storageProperties.getUploadDir());

            Files.createDirectories(uploadPath);

            String extension = FilenameUtils.getExtension(file.getOriginalFilename());

            String storedFileName =
                    UUID.randomUUID() + "." + extension;

            Path destination =
                    uploadPath.resolve(storedFileName);

            Files.copy(
                    file.getInputStream(),
                    destination,
                    StandardCopyOption.REPLACE_EXISTING);

            return new StoredFile(
                    storedFileName,
                    destination.toString());

        } catch (IOException ex) {

            throw new RuntimeException(
                    "Unable to store file",
                    ex);

        }

    }
    
    @Override
    public void delete(String storagePath) {

        try {

            Files.deleteIfExists(Paths.get(storagePath));

        } catch (IOException ex) {

            throw new RuntimeException(
                    "Unable to delete file.",
                    ex);

        }

    }

}
