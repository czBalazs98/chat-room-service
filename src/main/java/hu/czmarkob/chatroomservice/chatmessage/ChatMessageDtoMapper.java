package hu.czmarkob.chatroomservice.chatmessage;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface ChatMessageDtoMapper {

	@Mapping(target = "chatRoomId", source = "chatRoom.id")
	ChatMessageDto toDto(ChatMessage chatMessage);

	ChatMessage fromDto(ChatMessageDto chatMessageDto);
}
