package com.example.demo.controller;

import com.example.demo.cache.CacheStore;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

@Component
@RestController
public class ProxyController {
    private final CacheStore cacheStore = new CacheStore();

    @GetMapping("/fetch")
    public void fetchContent(@RequestParam String url, HttpServletResponse response) throws IOException {
        CacheStore.CachedResponse cachedResponse = cacheStore.get(url);
        if (cachedResponse != null) {
            response.addHeader("Cached-At", cachedResponse.cachedAt().toString());
            response.getWriter().write(cachedResponse.body());
            return;
        }

        URL remoteUrl = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) remoteUrl.openConnection();
        connection.setRequestMethod("GET");
        int status = connection.getResponseCode();

        if (status == HttpURLConnection.HTTP_OK) {
            Scanner scanner = new Scanner(connection.getInputStream()).useDelimiter("\\A");
            String content = scanner.hasNext() ? scanner.next() : "";
            Map<String, String> headers = new HashMap<>();
            connection.getHeaderFields().forEach((key, value) -> headers.put(key, String.join(", ", value)));

            LocalDateTime now = LocalDateTime.now();
            CacheStore.CachedResponse newCachedResponse = new CacheStore.CachedResponse(content, headers, now);
            cacheStore.put(url, newCachedResponse);

            response.addHeader("Cached-At", now.toString());
            response.getWriter().write(content);
        } else {
            throw new IllegalArgumentException("bad url");
        }
    }
}
