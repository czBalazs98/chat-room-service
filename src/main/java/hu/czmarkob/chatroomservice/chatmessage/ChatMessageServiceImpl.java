package hu.czmarkob.chatroomservice.chatmessage;

import hu.czmarkob.chatroomservice.chatroom.ChatRoom;
import hu.czmarkob.chatroomservice.chatroom.ChatRoomRepository;

import java.util.List;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;

@Slf4j
@RequiredArgsConstructor
public class ChatMessageServiceImpl implements ChatMessageService {

	private final ChatMessageRepository chatMessageRepository;

	private final ChatRoomRepository chatRoomRepository;

	private final ChatMessageDtoMapper chatMessageDtoMapper = Mappers.getMapper(ChatMessageDtoMapper.class);

	@Override
	public ChatMessageDto saveChatMessage(ChatMessageDto chatMessageDto) {
		ChatRoom chatRoom = chatRoomRepository.findById(chatMessageDto.getChatRoomId()).orElseThrow();

		ChatMessage message = chatMessageDtoMapper.fromDto(chatMessageDto);
		message.setChatRoom(chatRoom);

		ChatMessage savedMessage = chatMessageRepository.save(message);

		log.info("Chat message saved: " + savedMessage);

		return chatMessageDtoMapper.toDto(savedMessage);
	}

	@Override
	public List<ChatMessageDto> findChatMessagesByChatRoomId(Long chatRoomId) {
		return chatMessageRepository.findByChatRoomId(chatRoomId)
									.stream()
									.map(chatMessageDtoMapper::toDto)
									.toList();
	}
}
