package hu.czmarkob.chatroomservice.chatroom;

import org.mapstruct.Mapper;

@Mapper
public interface ChatRoomDtoMapper {

	ChatRoomDto toDto(ChatRoom chatRoom);

	ChatRoom fromDto(ChatRoomDto chatRoomDto);
}
