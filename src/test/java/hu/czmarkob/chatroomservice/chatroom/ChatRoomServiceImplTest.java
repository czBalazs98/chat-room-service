package hu.czmarkob.chatroomservice.chatroom;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ChatRoomServiceImplTest {

	private static final String CHAT_ROOM_NAME = "test";

	@Mock
	private ChatRoomRepository chatRoomRepository;

	private ChatRoomServiceImpl chatRoomService;

	private final ChatRoomDtoMapper mapper = Mappers.getMapper(ChatRoomDtoMapper.class);

	private ChatRoom chatRoom;

	private ChatRoomDto chatRoomDto;

	@BeforeEach
	void setUp() {
		chatRoomService = new ChatRoomServiceImpl(chatRoomRepository);

		chatRoom = ChatRoomRepositoryTest.generateChatRoom();
		chatRoomDto = mapper.toDto(chatRoom);
	}

	@Test
	void test_find_chat_rooms() {
		given(chatRoomRepository.findAll()).willReturn(List.of(chatRoom));

		List<ChatRoomDto> chatRooms = chatRoomService.findChatRooms();

		assertThat(chatRooms).hasSize(1);
		verify(chatRoomRepository, times(1)).findAll();
	}

	@Test
	void test_find_chat_rooms_by_name_contains() {
		given(chatRoomRepository.findByNameContains(CHAT_ROOM_NAME)).willReturn(List.of(chatRoom));

		List<ChatRoomDto> chatRooms = chatRoomService.findChatRoomsByName(CHAT_ROOM_NAME);

		assertThat(chatRooms).hasSize(1);
		verify(chatRoomRepository, times(1)).findByNameContains(CHAT_ROOM_NAME);
	}

	@Test
	void test_create_chat_room() {
		given(chatRoomRepository.save(chatRoom)).willReturn(chatRoom);

		ChatRoomDto createdChatRoomDto = chatRoomService.createChatRoom(chatRoomDto);

		assertThat(createdChatRoomDto).isEqualTo(chatRoomDto);
		verify(chatRoomRepository, times(1)).save(chatRoom);
	}
}
