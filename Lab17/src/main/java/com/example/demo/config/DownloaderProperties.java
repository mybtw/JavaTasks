package com.example.demo.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "downloader")
@Data
public class DownloaderProperties {
    public int maxThreads;
    public long bytesPerSecond;
    public String linksFilePath;
    public String downloadDirectoryPath;
}
