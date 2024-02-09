package hu.czmarkob.chatroomservice.config;

import hu.czmarkob.chatroomservice.chatmessage.ChatMessageWebSocketHandler;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebsocketConfig implements WebSocketConfigurer {

	@Value("${chat-room-service.ui-url}")
	private String chatRoomUiUrl;

	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		registry.addHandler(chatMessageWebSocketHandler(), "/chat").setAllowedOrigins(chatRoomUiUrl);
	}

	@Bean
	public ChatMessageWebSocketHandler chatMessageWebSocketHandler() {
		return new ChatMessageWebSocketHandler();
	}
}
