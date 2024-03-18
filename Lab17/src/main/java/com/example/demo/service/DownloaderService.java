package com.example.demo.service;

import com.example.demo.config.DownloaderProperties;
import com.example.demo.repository.DownloaderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
@RequiredArgsConstructor
public class DownloaderService {
    private final DownloaderProperties downloaderProperties;
    private final DownloaderRepository downloaderRepository;

    public void downloadFiles() throws IOException, InterruptedException {
        List<String> links = downloaderRepository.getLinksFromFile(downloaderProperties.linksFilePath);
        ExecutorService executorService = Executors.newFixedThreadPool(downloaderProperties.maxThreads);

        for (String link : links) {
            executorService.submit(() -> {
                downloaderRepository.downloadFile(link, downloaderProperties.downloadDirectoryPath, downloaderProperties.bytesPerSecond);
            });
        }
        executorService.shutdown();

        while (!executorService.isTerminated()) {
            Thread.sleep(1000);
        }
    }
}
