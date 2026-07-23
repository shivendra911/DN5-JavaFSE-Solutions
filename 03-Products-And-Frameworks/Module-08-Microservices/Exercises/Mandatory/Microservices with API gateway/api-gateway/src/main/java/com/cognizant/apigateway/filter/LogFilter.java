package com.cognizant.apigateway.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

// Hands-on step 21: Global filter that logs every request passing through the gateway
// This runs for EVERY route, not just one specific service

@Component
public class LogFilter implements GlobalFilter, Ordered {

    private static final Logger LOGGER = LoggerFactory.getLogger(LogFilter.class);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        // log the incoming request's path before it gets routed to the actual microservice
        LOGGER.info("Incoming request -> Method: {}, Path: {}",
                exchange.getRequest().getMethod(),
                exchange.getRequest().getURI().getPath());

        // must call chain.filter() to let the request continue on to its destination
        // forgetting this line would block every single request at the gateway
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        // runs this filter early, before the request is routed
        return -1;
    }
}
