package com.example.demo.config;

import com.example.demo.terminal.Terminal;
import com.example.demo.terminal.TerminalImpl;
import com.example.demo.terminal.server.TerminalServer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TerminalConfig {
    @Bean
    public String correctPin() {
        return "0000";
    }

}
