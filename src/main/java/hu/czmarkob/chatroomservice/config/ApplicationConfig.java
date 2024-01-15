package hu.czmarkob.chatroomservice.config;

import hu.czmarkob.chatroomservice.chatroom.ChatRoomRepository;
import hu.czmarkob.chatroomservice.chatroom.ChatRoomService;
import hu.czmarkob.chatroomservice.chatroom.ChatRoomServiceImpl;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

	@Bean
	ChatRoomService chatRoomService(ChatRoomRepository chatRoomRepository) {
		return new ChatRoomServiceImpl(chatRoomRepository);
	}
}
