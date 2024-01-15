package hu.czmarkob.chatroomservice.chatroom;

import java.util.List;

public interface ChatRoomService {

	List<ChatRoomDto> findChatRooms();

	List<ChatRoomDto> findChatRoomsByName(String name);

	ChatRoomDto createChatRoom(ChatRoomDto chatRoomDto);
}
