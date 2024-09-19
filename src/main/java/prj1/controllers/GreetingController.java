package prj1.controllers;

import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.messaging.handler.annotation.MessageMapping;

import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.HtmlUtils;
import prj1.models.websocket.Greeting;
import prj1.models.websocket.HelloMessage;
import reactor.core.publisher.Sinks;

import java.time.LocalDateTime;

@RestController
//@Controller
@RequiredArgsConstructor
public class GreetingController {
    private final Sinks.Many<String> sink;

    //    @MessageMapping("/chat")
//    @SendTo("/topic/greetings")
//    public Greeting greeting(String message) throws Exception {
//        Thread.sleep(1000); // simulated delay
//        return new Greeting("Hello, " + message + "!");
//    }
    @PostMapping("/send")
    public void demo(
            @RequestBody String message, ServerHttpRequest request
    ) {
        sink.emitNext("[" +LocalDateTime.now() +  "]  " + (CollectionUtils.isNotEmpty(request.getHeaders().get("user")) ? request.getHeaders().get("user").get(0) : request.getId()) + " : " + message, Sinks.EmitFailureHandler.FAIL_FAST);
    }
}
