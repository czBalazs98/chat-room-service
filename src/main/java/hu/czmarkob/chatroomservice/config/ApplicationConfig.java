package hu.czmarkob.chatroomservice.config;

import hu.czmarkob.chatroomservice.chatmessage.ChatMessageRepository;
import hu.czmarkob.chatroomservice.chatmessage.ChatMessageService;
import hu.czmarkob.chatroomservice.chatmessage.ChatMessageServiceImpl;
import hu.czmarkob.chatroomservice.chatroom.ChatRoomRepository;
import hu.czmarkob.chatroomservice.chatroom.ChatRoomService;
import hu.czmarkob.chatroomservice.chatroom.ChatRoomServiceImpl;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing
public class ApplicationConfig {

	@Bean
	ChatRoomService chatRoomService(ChatRoomRepository chatRoomRepository) {
		return new ChatRoomServiceImpl(chatRoomRepository);
	}

	@Bean
	ChatMessageService chatMessageService(ChatMessageRepository chatMessageRepository, ChatRoomRepository chatRoomRepository) {
		return new ChatMessageServiceImpl(chatMessageRepository, chatRoomRepository);
	}
}
