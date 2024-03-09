package com.example.demo.controllers;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.Duration;

@RestController
public class ChunkedJsonController {

    @GetMapping
    public String home() {
        return "index";
    }

    @GetMapping(value = "/chunked-json", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public Flux<String> getChunkedJson() {
        return Flux.just(
                        "{\"field\":\"value1\"}",
                        "{\"field\":\"value2\"}",
                        "{\"field\":\"value3\"}",
                        "{\"field\":\"value4\"}",
                        "{\"field\":\"value5\"}")
                .doOnCancel(() -> System.out.println("Client disconnected"))
                .delayElements(Duration.ofSeconds(5));
    }
}
