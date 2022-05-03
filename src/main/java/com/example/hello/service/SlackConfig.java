package com.example.hello.service;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Getter
@Setter
@Configuration
@PropertySource("classpath:slack.properties")
public class SlackConfig {
    @Value("${token}")
    private String token;

    @Value("${channel.errorlog}")
    private String errorlogChannel;
}
