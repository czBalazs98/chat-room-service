package hu.czmarkob.chatroomservice.chatroom;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


@DataJpaTest
public class ChatRoomRepositoryTest {

	@Autowired
	private ChatRoomRepository chatRoomRepository;

	@BeforeEach
	void set_up() {
		ChatRoom chatRoom = generateChatRoom();
		chatRoomRepository.save(chatRoom);
	}

	@Test
	void find_by_name_contains() {
		List<ChatRoom> chatRooms = chatRoomRepository.findByNameContains("test");

		assertThat(chatRooms).isNotEmpty();
		assertThat(chatRooms.get(0).getName()).contains("test");
	}

	@Test
	void find_by_name_contains_no_results() {
		List<ChatRoom> chatRooms = chatRoomRepository.findByNameContains("not_exists");

		assertThat(chatRooms).isEmpty();
	}

	public static ChatRoom generateChatRoom() {
		ChatRoom chatRoom = new ChatRoom();
		chatRoom.setId(1L);
		chatRoom.setName("test chatroom");
		chatRoom.setRules("1.) test rule");
		chatRoom.setTags(List.of("test1", "test2"));
		chatRoom.setDescription("test description");
		return chatRoom;
	}
}
