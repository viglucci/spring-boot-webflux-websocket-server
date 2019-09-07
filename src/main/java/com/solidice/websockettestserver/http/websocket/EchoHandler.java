package com.solidice.websockettestserver.http.websocket;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketSession;
import reactor.core.publisher.Mono;

/**
 * @author Kevin
 * @since 9/6/2019
 */
@Component("EchoHandler")
public class EchoHandler implements WebSocketHandler {
	@Override
	public Mono<Void> handle(WebSocketSession session) {
		return session
				.send(session.receive()
						.map(msg -> "RECEIVED ON SERVER :: " + msg.getPayloadAsText())
						.map(session::textMessage)
				);
	}
}
