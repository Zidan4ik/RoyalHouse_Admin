package com.example.royalhouse.util;

import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

public class Image {
    public static String getPathToFileImage(MultipartFile file) throws IOException {
        String nameFile = StringUtils.cleanPath(file.getOriginalFilename());
        String uuidFile = UUID.randomUUID().toString();

        String uploadDir = "./uploads/";

        Path uploadPath = Paths.get(uploadDir);


        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        InputStream inputStream = file.getInputStream();
        Path filePath = uploadPath.resolve(uuidFile + "." + nameFile);
        Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);

        return uuidFile + "." + nameFile;
    }

    public static void saveFile(String uploadDir, MultipartFile multipartFile, String fileName) throws IOException {
        Path uploadPath = Paths.get(uploadDir);

        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }
        try (InputStream inputStream = multipartFile.getInputStream()) {
            Path filePath = uploadPath.resolve(fileName);

            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
            throw new IOException("Could not save uploaded file: " + fileName);
        }
    }
}
