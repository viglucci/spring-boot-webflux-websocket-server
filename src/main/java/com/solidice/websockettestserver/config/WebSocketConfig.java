package com.solidice.websockettestserver.config;

import com.solidice.websockettestserver.http.websocket.EchoHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.HandlerMapping;
import org.springframework.web.reactive.handler.SimpleUrlHandlerMapping;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.server.WebSocketService;
import org.springframework.web.reactive.socket.server.support.HandshakeWebSocketService;
import org.springframework.web.reactive.socket.server.support.WebSocketHandlerAdapter;
import org.springframework.web.reactive.socket.server.upgrade.ReactorNettyRequestUpgradeStrategy;

import java.util.Collections;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @author Kevin
 * @since 9/6/2019
 */
@Configuration
class WebSocketConfig {

	@Autowired
	@Qualifier("EchoHandler")
	private WebSocketHandler echoHandler;

	@Bean
	Executor executor() {
		return Executors.newSingleThreadExecutor();
	}

	@Bean
	public HandlerMapping webSocketHandlerMapping() {
		return new SimpleUrlHandlerMapping() {
			{
				setUrlMap(Collections.singletonMap("/ws/echo", echoHandler));
				setOrder(1);
			}
		};
	}

	@Bean
	public WebSocketHandlerAdapter handlerAdapter() {
		return new WebSocketHandlerAdapter(webSocketService());
	}

	@Bean
	public WebSocketService webSocketService() {
		return new HandshakeWebSocketService(new ReactorNettyRequestUpgradeStrategy());
	}
}