package hu.czmarkob.chatroomservice.chatmessage;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/chat-messages")
@RequiredArgsConstructor
public class ChatMessageController {

	private final ChatMessageService chatMessageService;

	@GetMapping("/{chatRoomId}")
	public List<ChatMessageDto> findChatMessagesByChatRoomId(@PathVariable Long chatRoomId) {
		return chatMessageService.findChatMessagesByChatRoomId(chatRoomId);
	}

	@PostMapping
	public ChatMessageDto saveChatMessage(@RequestBody ChatMessageDto chatMessageDto) {
		return chatMessageService.saveChatMessage(chatMessageDto);
	}

}
