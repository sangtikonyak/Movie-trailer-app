package com.capstone.ApiGateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder){

        return builder.routes().
                route(p->p.path("/api/v2/**")
                        .uri("lb://favouriteService"))
                .route(p->p.path("/api/v3/**")
                        .uri("lb://notification-service"))
                .route(p->p.path("/api/v1/**")
                        .uri("lb://UserAuthService"))
                .route(p->p.path("/watch-later/**")
                        .uri("lb://watchLaterService")).build();
    }
}
