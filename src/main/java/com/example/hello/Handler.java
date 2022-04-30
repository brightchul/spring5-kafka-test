package com.example.hello;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class Handler {

    @Autowired
    private KafkaTemplate<String, String> template;

    public Mono<ServerResponse> getHello(ServerRequest request) {
        this.template.send("topic1", new Foo2("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!").toString());
        return ServerResponse.ok().body(Mono.just("hello"), String.class);
    }

    public Mono<ServerResponse> getHelloId(ServerRequest request) {
        String id = request.pathVariable("id");
        Greeting greeting = new Greeting("message : " + id, id);
        Mono<Greeting> monoGreeting = Mono.just(greeting);

        this.template.send("topic1.DLT", greeting.toString());

        Mono<ServerResponse> serverResponse =
                ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(monoGreeting, String.class);

        return serverResponse;
    }
}
