package com.example.demo.repository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@Repository
@Slf4j
public class DownloaderRepository {
    public List<String> getLinksFromFile(String filePath) throws IOException {
        return Files.readAllLines(Paths.get(filePath));
    }

    public void downloadFile(String link, String downloadDirectoryPath, long bytesPerSecond) {
        try {
            URL url = new URL(link);
            File destFile = new File(downloadDirectoryPath, Paths.get(url.getPath()).getFileName().toString());

            try (InputStream is = url.openStream();
                 OutputStream os = new BufferedOutputStream(new FileOutputStream(destFile))) {
                byte[] buffer = new byte[1024];
                int bytesRead;
                long startTime = System.currentTimeMillis();

                while ((bytesRead = is.read(buffer)) != -1) {
                    os.write(buffer, 0, bytesRead);
                    long timeSpent = System.currentTimeMillis() - startTime;
                    long expectedTime = bytesRead * 1000L / bytesPerSecond;

                    if (expectedTime > timeSpent) {
                        Thread.sleep(expectedTime - timeSpent);
                    }

                    startTime = System.currentTimeMillis();
                }
            }
            log.info("Файл загружен: {}", destFile.getAbsolutePath());
        } catch (Exception e) {
            log.info("Ошибка при загрузке: {}", e.getMessage());
        }
    }
}
