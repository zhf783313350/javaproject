package com.example.demo.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class AuthorizationFilter implements GlobalFilter, Ordered {

    private static final Logger log = LoggerFactory.getLogger(AuthorizationFilter.class);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String path = exchange.getRequest().getURI().getPath();
        log.info("Gateway intercepting request: {}", path);

        // Simulated authentication block
        // In a real Million-RMB system, this validates a JWT Token from the headers.
        String token = exchange.getRequest().getHeaders().getFirst("Authorization");

        // For demonstration, we simply log and allow all routes containing /api/v1/
        if (path.contains("/api/v1/")) {
            log.info("Routing external request to backend microservices.");
            return chain.filter(exchange);
        }

        log.warn("Unauthorized access blocked by Gateway.");
        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
        return exchange.getResponse().setComplete();
    }

    @Override
    public int getOrder() {
        // High priority filter
        return -100;
    }
}
