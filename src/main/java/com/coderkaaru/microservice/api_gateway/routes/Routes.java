package com.coderkaaru.microservice.api_gateway.routes;

import org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions;
import org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.*;

import static org.springframework.cloud.gateway.server.mvc.filter.FilterFunctions.setPath;

@Configuration
public class Routes {
    @Bean
    public RouterFunction<ServerResponse> ProductServiceRoute() {
        return GatewayRouterFunctions.route("product_service")
                .route(RequestPredicates.path("/api/product"), HandlerFunctions.http("http://localhost:8080"))
                .build();
    }
    @Bean
    public RouterFunction<ServerResponse> ProductServiceSwaggerRoute() {
        return GatewayRouterFunctions.route("product_service_swagger")
                .route(RequestPredicates.path("/aggregate/product-service/v3/api-docs"), HandlerFunctions.http("http://localhost:8080"))
                .filter(setPath("/api-docs"))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> OrderServiceRoute() {
        return GatewayRouterFunctions.route("order_service")
                .route(RequestPredicates.path("/api/order"), HandlerFunctions.http("http://localhost:8081"))
                .build();

    }
    @Bean
    public RouterFunction<ServerResponse> OrderServiceSwaggerRoute() {
        return GatewayRouterFunctions.route("order_service_swagger")
                .route(RequestPredicates.path("/aggregate/order-service/v3/api-docs"), HandlerFunctions.http("http://localhost:8081"))
                .filter(setPath("/api-docs"))
                .build();
    }

}