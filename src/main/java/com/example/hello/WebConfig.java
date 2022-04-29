package com.example.hello;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;


@Configuration
public class WebConfig {
    @Bean
    public RouterFunction<ServerResponse> getHello(Handler handler) {
        return route(GET("/hello"), handler::getHello)
                .andRoute(GET("/hello/{id}"), handler::getHelloId);
    }
}
