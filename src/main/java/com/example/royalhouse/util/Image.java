package com.example.royalhouse.util;

import com.example.royalhouse.entity.Object;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class Image {
    public static void saveFiles(Long id, HashMap<String,MultipartFile> map) {
        String uploadDir = "./uploads/" + id;
        try {
            for (Map.Entry<String, MultipartFile> m:map.entrySet()){
                if (!m.getValue().getOriginalFilename().isEmpty()) {
                    Image.saveFile(uploadDir, m.getValue(), m.getKey());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
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
            e.printStackTrace();
        }
    }
}
