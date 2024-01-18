package hu.czmarkob.chatroomservice.chatmessage;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import hu.czmarkob.chatroomservice.chatroom.ChatRoom;
import hu.czmarkob.chatroomservice.chatroom.ChatRoomRepository;
import hu.czmarkob.chatroomservice.chatroom.ChatRoomRepositoryTest;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ChatMessageServiceImplTest {

	@Mock
	private ChatRoomRepository chatRoomRepository;

	@Mock
	private ChatMessageRepository chatMessageRepository;

	private final ChatMessageDtoMapper mapper = Mappers.getMapper(ChatMessageDtoMapper.class);

	private ChatMessageServiceImpl chatMessageService;

	private ChatRoom chatRoom;

	private ChatMessage chatMessage;

	private ChatMessageDto chatMessageDto;

	@BeforeEach
	void set_up() {
		chatMessageService = new ChatMessageServiceImpl(chatMessageRepository, chatRoomRepository);

		chatRoom = ChatRoomRepositoryTest.generateChatRoom();
		chatMessage = ChatMessageRepositoryTest.generateChatMessage(chatRoom);
		chatMessageDto = mapper.toDto(chatMessage);
	}

	@Test
	void test_save_chat_message() {
		given(chatRoomRepository.findById(chatRoom.getId())).willReturn(Optional.of(chatRoom));
		given(chatMessageRepository.save(chatMessage)).willReturn(chatMessage);

		ChatMessageDto savedChatMessageDto = chatMessageService.saveChatMessage(chatMessageDto);

		assertThat(savedChatMessageDto).isEqualTo(chatMessageDto);
		verify(chatRoomRepository, times(1)).findById(chatRoom.getId());
		verify(chatMessageRepository, times(1)).save(chatMessage);
	}

	@Test
	void test_save_chat_message_room_does_not_exists() {
		given(chatRoomRepository.findById(anyLong())).willReturn(Optional.empty());

		assertThrows(NoSuchElementException.class, () -> chatMessageService.saveChatMessage(chatMessageDto));
		verify(chatRoomRepository, times(1)).findById(anyLong());
	}

	@Test
	void test_find_chat_messages_by_chat_room_id() {
		given(chatMessageRepository.findByChatRoomId(chatRoom.getId())).willReturn(List.of(chatMessage));

		List<ChatMessageDto> messages = chatMessageService.findChatMessagesByChatRoomId(chatRoom.getId());

		assertThat(messages).hasSize(1);
		verify(chatMessageRepository, times(1)).findByChatRoomId(chatRoom.getId());
	}
}
