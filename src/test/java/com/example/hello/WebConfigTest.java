package com.example.hello;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.assertj.core.api.Assertions.assertThat;


//@SpringBootTest
//class WebConfigTest {
//
//    @Autowired
//    WebConfig webConfig;
//
//    @Test
//    void getHello() {
//        WebTestClient webTestClient = WebTestClient.bindToRouterFunction(webConfig.getHello()).build();
//        webTestClient.get().uri("/hello").exchange().expectBody(String.class).value(o -> {
//            assertThat(o).isEqualTo("hello");
//        });
//    }
//}