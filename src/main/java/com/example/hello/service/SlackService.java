package com.example.hello.service;

import com.example.hello.HelloApplication;
import com.slack.api.Slack;
import com.slack.api.methods.SlackApiException;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@Slf4j
public class SlackService {
    @Autowired
    SlackConfig slackConfig;

    private final Logger logger = LoggerFactory.getLogger(HelloApplication.class);

    public void sendMessage(String channel, String message) {
        Slack slack = Slack.getInstance();
        try {
            slack.methods(slackConfig.getToken()).chatPostMessage(req -> req.channel(channel).text(message));
        } catch(SlackApiException | IOException e) {
            logger.error(e.getMessage());
        }
    }

    public void sendErrorMessage(String errorMessage) {
        sendMessage(slackConfig.getErrorlogChannel(), errorMessage);
    }
}
