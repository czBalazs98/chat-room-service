package hu.czmarkob.chatroomservice.chatroom;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;

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
		return mapper.toDto(chatRoom);
	}
}
