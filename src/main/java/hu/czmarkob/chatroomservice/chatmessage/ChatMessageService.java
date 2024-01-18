package hu.czmarkob.chatroomservice.chatmessage;

import java.util.List;

public interface ChatMessageService {

	ChatMessageDto saveChatMessage(ChatMessageDto chatMessageDto);

	List<ChatMessageDto> findChatMessagesByChatRoomId(Long chatRoomId);
}
