package hu.czmarkob.chatroomservice.chatmessage;

import static org.assertj.core.api.Assertions.assertThat;

import hu.czmarkob.chatroomservice.chatroom.ChatRoom;
import hu.czmarkob.chatroomservice.chatroom.ChatRoomRepository;
import hu.czmarkob.chatroomservice.chatroom.ChatRoomRepositoryTest;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class ChatMessageRepositoryTest {

	@Autowired
	private ChatRoomRepository chatRoomRepository;

	@Autowired
	private ChatMessageRepository chatMessageRepository;

	@BeforeEach
	void set_up() {
		ChatRoom chatRoom = ChatRoomRepositoryTest.generateChatRoom();
		chatRoomRepository.save(chatRoom);

		ChatMessage chatMessage = generateChatMessage(chatRoom);
		chatMessageRepository.save(chatMessage);
	}

	@Test
	void find_by_chat_room_id() {
		List<ChatMessage> messages = chatMessageRepository.findByChatRoomId(1L);

		assertThat(messages).isNotEmpty();
		assertThat(messages.get(0).getId()).isEqualTo(1L);
	}

	@Test
	void find_by_chat_room_id_no_results() {
		List<ChatMessage> messages = chatMessageRepository.findByChatRoomId(5L);

		assertThat(messages).isEmpty();
	}

	public static ChatMessage generateChatMessage(ChatRoom chatRoom) {
		ChatMessage chatMessage = new ChatMessage();
		chatMessage.setId(1L);
		chatMessage.setCreated(LocalDateTime.now());
		chatMessage.setMessage("test message");
		chatMessage.setSender("testuser");
		chatMessage.setChatRoom(chatRoom);
		return chatMessage;
	}
}
