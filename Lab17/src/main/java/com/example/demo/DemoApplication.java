package com.example.demo;

import com.example.demo.service.DownloaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

    private final DownloaderService downloaderService;

    @Autowired
    public DemoApplication(DownloaderService downloaderService) {
        this.downloaderService = downloaderService;
    }

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        downloaderService.downloadFiles();
    }

}
