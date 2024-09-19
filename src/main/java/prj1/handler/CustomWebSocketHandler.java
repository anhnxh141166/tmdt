package prj1.handler;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketMessage;
import org.springframework.web.reactive.socket.WebSocketSession;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
@RequiredArgsConstructor
public class CustomWebSocketHandler implements WebSocketHandler {

    private final Map < String, WebSocketSession > sessions = new ConcurrentHashMap < > ();
    private final Sinks.Many<String> sink;

    @Override
    public Mono < Void > handle(WebSocketSession session) {
        sessions.put(session.getId(), session);

//        Sinks.Many < String > userSink = Sinks.many().unicast().onBackpressureBuffer();
        Flux < String > receive = session.receive().map(WebSocketMessage::getPayloadAsText);
        return session.send(
                        sink.asFlux()
                                .mergeWith(receive)
                                .map(session::textMessage)
                )
                .doFinally(signalType -> {
                    sessions.remove(session.getId());
                });

    }

    public void sendMessageToUser(String sessionId, String message) {
        WebSocketSession webSocketSession = sessions.get(sessionId);
        webSocketSession.send(Mono.just(webSocketSession.textMessage(message))).subscribe();
    }
}