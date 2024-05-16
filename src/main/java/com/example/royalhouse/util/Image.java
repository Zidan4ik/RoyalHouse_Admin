package com.example.royalhouse.util;


import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Image {
    public static void saveFiles(String uploadDir, HashMap<String, MultipartFile> map) {
        try {
            for (Map.Entry<String, MultipartFile> m : map.entrySet()) {
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

    public static void saveAfterDelete(String uploadDir, MultipartFile multipartFile, String fileName) throws IOException {
        Path uploadPath = Paths.get(uploadDir);

        if (Files.exists(uploadPath)) {
            List<Path> paths = Files.list(uploadPath).collect(Collectors.toList());
            for (Path p : paths) {
                Files.delete(p);
            }
        }
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

    public static void savesAfterDelete(String uploadDir, List<String> filesNames, List<MultipartFile> files) throws IOException {
        Path uploadPath = Paths.get(uploadDir);

        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }
        for (int i = 0; i < files.size(); i++) {
            try (InputStream inputStream = files.get(i).getInputStream()) {
                Path filePath = uploadPath.resolve(filesNames.get(i));
                Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void fullDelete(String uploadDir) {
        Path uploadPath = Paths.get(uploadDir);

        if (Files.exists(uploadPath)) {
            try {
                Files.walk(uploadPath)
                        .sorted(Comparator.reverseOrder())
                        .map(Path::toFile)
                        .forEach(File::delete);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void deleteFiles(String uploadDir, List<String> namesDelete)  {
        Path uploadPath = Paths.get(uploadDir);

        if (Files.exists(uploadPath)) {
            try {
                Files.walk(uploadPath)
                        .map(Path::toFile)
                        .forEach(file -> {
                            if (namesDelete.contains(file.getName())) {
                                try {
                                    Files.delete(file.toPath());
                                } catch (IOException e) {
                                    System.err.println("Помилка при видаленні файлу: " + e.getMessage());
                                }
                            }
                        });
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
