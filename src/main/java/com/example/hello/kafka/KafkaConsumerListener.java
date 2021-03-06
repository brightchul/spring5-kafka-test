package com.example.hello.kafka;

import com.example.hello.Foo2;
import com.example.hello.HelloApplication;
import com.example.hello.service.SlackService;
import com.slack.api.Slack;
import com.slack.api.methods.SlackApiException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

@Component
@EnableKafka
public class KafkaConsumerListener {
    @Autowired
    SlackService slackService;


    public final CountDownLatch countDownLatch1 = new CountDownLatch(1000);
    private final TaskExecutor exec = new SimpleAsyncTaskExecutor();
    private final Logger logger = LoggerFactory.getLogger(HelloApplication.class);

    @KafkaListener(id = " counterDown", topics = "topic1")
    public void listenCounterDown(Foo2 foo) {
        countDownLatch1.countDown();
        logger.info(countDownLatch1.getCount() + "");
        slackService.sendErrorMessage(countDownLatch1.getCount() + "");
    }

    @KafkaListener(id = "fooGroup", topics = "topic1")
    public void topic1listen(Foo2 foo) {
        logger.info("Received: " + foo);
        this.exec.execute(() -> System.out.println("Hit Enter to terminate..."));

    }

    @KafkaListener(id = "dltGroup", topics = "topic1.DLT")
    public void topic1DltListen(String in) {
        logger.info("Received from DLT: " + in);
        this.exec.execute(() -> System.out.println("Hit Enter to terminate..."));
    }

}
