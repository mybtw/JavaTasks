package ru.astaf.apigateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.cors.CorsConfiguration;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {
    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity serverHttpSecurity) {
        serverHttpSecurity
                .csrf(ServerHttpSecurity.CsrfSpec::disable)
                .authorizeExchange(exchange ->
                        exchange.pathMatchers("/eureka/**")
                                .permitAll()
                                .pathMatchers("/**").hasAuthority("SCOPE_message.read"))
                .oauth2ResourceServer(spec -> spec.jwt(Customizer.withDefaults()))
                .cors(corsSpec -> corsSpec.configurationSource(request -> {
                    CorsConfiguration corsConfig = new CorsConfiguration();
                    corsConfig.setAllowedOrigins(List.of("http://localhost:3000"));
                    corsConfig.setAllowedMethods(Arrays.asList("GET", "POST", "DELETE"));
                    corsConfig.setAllowedHeaders(List.of("*"));
                    return corsConfig;
                }))
                .addFilterAfter(new JwtClaimsToHeadersFilter(), SecurityWebFiltersOrder.AUTHENTICATION);
        return serverHttpSecurity.build();
    }
}
/*

 return http
            .authorizeExchange(exchanges -> exchanges
                    .pathMatchers("/**")
                    .access((mono, context) -> mono
                            .map(auth -> auth.hasAuthority("SCOPE_message.read"))
                            .map(AuthorizationDecision::new))
            )
            .oauth2ResourceServer(oauth2 -> oauth2
                    .jwt())
            .build();






serverHttpSecurity
                .csrf().disable()
                .authorizeExchange(exchange -> exchange
                        .pathMatchers("/eureka/**").permitAll()
                        .pathMatchers(HttpMethod.OPTIONS, "/**").permitAll() // Allow preflight requests
                        .anyExchange().access((authentication, context) ->
                                authentication
                                    .map(auth -> auth.getAuthorities().stream()
                                        .anyMatch(grantedAuthority ->
                                            "SCOPE_message.read".equals(grantedAuthority.getAuthority()))
                                        ? ServerHttpSecurity.AccessDecisionManagerResult.granted()
                                        : ServerHttpSecurity.AccessDecisionManagerResult.denied())
                                    .defaultIfEmpty(ServerHttpSecurity.AccessDecisionManagerResult.denied()))
                )
                .oauth2ResourceServer(oauth2 -> oauth2.jwt())
                .cors(corsSpec -> corsSpec.configurationSource(request -> {
                    CorsConfiguration corsConfig = new CorsConfiguration();
                    corsConfig.setAllowedOrigins(List.of("http://localhost:3000")); // Allowed origins
                    corsConfig.setAllowedMethods(Arrays.asList("GET", "POST")); // Allowed methods
                    corsConfig.setAllowedHeaders(List.of("*")); // Allowed headers
                    return corsConfig;
                }));
        return serverHttpSecurity.build();*/