package com.example.appgateway;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppGatewayConfiguration {
    @Bean
    public RouteLocator routerLocator(RouteLocatorBuilder locatorBuilder){
        return locatorBuilder.routes()
                .route(p->p.path("/send/**")
                        .uri("lb://mailing-service")
                )
                .route(p->p.path("/register/**")
                        .uri("lb://registration-service")
                )
                .route(p->p.path("/vote/**")
                        .uri("lb://voting-service")
                )
                .route(p->p.path("/data/**")
                        .uri("lb://data-service")
                )
                .build();
    }
}
