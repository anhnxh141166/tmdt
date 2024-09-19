package prj1.configurations;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.HandlerAdapter;
import org.springframework.web.reactive.HandlerMapping;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.handler.SimpleUrlHandlerMapping;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.server.support.WebSocketHandlerAdapter;
import reactor.core.publisher.Sinks;

import java.util.Map;

@Configuration
//@EnableWebSocketMessageBroker
@RequiredArgsConstructor
@EnableWebFlux
public class WebSocketConfig
//        implements WebSocketMessageBrokerConfigurer
{
//    private final CustomWebSocketHandler customWebSocketHandler;


    @Bean
    public Sinks.Many<String> sink(){
        return Sinks.many().multicast().directBestEffort();
    }

    @Bean
    public HandlerMapping simpleUrlHandlerMapping(WebSocketHandler handler){

        Map<String, WebSocketHandler> map = Map.of("/ws/messages", handler);

        return  new SimpleUrlHandlerMapping(map,-1);
    }


    @Bean
    public HandlerAdapter webSocketHandlerAdapter(){
        return  new WebSocketHandlerAdapter();
    }


//    @Bean
//    public WebSocketHandlerAdapter handlerAdapter() {
//        return new WebSocketHandlerAdapter(webSocketService());
//    }
//
//    @Bean
//    public WebSocketService webSocketService() {
//        TomcatRequestUpgradeStrategy strategy = new TomcatRequestUpgradeStrategy();
//        strategy.setMaxSessionIdleTimeout(0L);
//        return new HandshakeWebSocketService(strategy);
//    }


//    @Override
//    public void configureMessageBroker(MessageBrokerRegistry config) {
//        config.enableSimpleBroker("/topic");
//        config.setApplicationDestinationPrefixes("/app");
//    }
//
//    @Override
//    public void registerStompEndpoints(StompEndpointRegistry registry) {
//        registry.addEndpoint("/ws/hello")
////                .setAllowedOrigins("http://localhost:4200")
//                .setAllowedOrigins("*");
//        registry.addEndpoint("/ws/hello")
////                .setAllowedOrigins("http://localhost:4200")
//                .setAllowedOrigins("*")
//                .withSockJS();
//
//    }
}
