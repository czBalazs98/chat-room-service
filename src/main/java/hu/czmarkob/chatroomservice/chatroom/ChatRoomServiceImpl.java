package hu.czmarkob.chatroomservice.chatroom;

import java.util.List;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;

@Slf4j
@RequiredArgsConstructor
public class ChatRoomServiceImpl implements ChatRoomService {

	private final ChatRoomRepository chatRoomRepository;

	private final ChatRoomDtoMapper mapper = Mappers.getMapper(ChatRoomDtoMapper.class);

	@Override
	public List<ChatRoomDto> findChatRooms() {
		return chatRoomRepository.findAll()
								 .stream()
								 .map(mapper::toDto)
								 .toList();
	}

	@Override
	public List<ChatRoomDto> findChatRoomsByName(String name) {
		return chatRoomRepository.findByNameContains(name)
								 .stream()
								 .map(mapper::toDto)
								 .toList();
	}

	@Override
	public ChatRoomDto createChatRoom(ChatRoomDto chatRoomDto) {
		ChatRoom chatRoom = chatRoomRepository.save(mapper.fromDto(chatRoomDto));

		log.info("Chat message saved: " + chatRoom);

		return mapper.toDto(chatRoom);
	}
}
