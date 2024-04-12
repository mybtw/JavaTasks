package ru.astaf.apigateway.config;

import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

public class JwtClaimsToHeadersFilter implements WebFilter {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        return ReactiveSecurityContextHolder.getContext()
                .map(securityContext -> securityContext.getAuthentication())
                .filter(authentication -> authentication.getCredentials() instanceof Jwt)
                .map(authentication -> (Jwt) authentication.getCredentials())
                .map(jwt -> {
                    String exampleClaim = jwt.getClaim("preferred_username");
                    exchange.getRequest().mutate().header("X-Preferred-Username", exampleClaim).build();
                    return exchange;
                })
                .defaultIfEmpty(exchange)
                .flatMap(chain::filter);
    }
}