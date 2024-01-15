package hu.czmarkob.chatroomservice.chatroom;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/chat-rooms")
@RequiredArgsConstructor
public class ChatRoomController {

	private final ChatRoomService chatRoomService;

	@GetMapping
	public List<ChatRoomDto> findChatRooms() {
		return chatRoomService.findChatRooms();
	}

	@GetMapping("/{name}")
	public List<ChatRoomDto> findChatRoomsByName(@PathVariable String name) {
		return chatRoomService.findChatRoomsByName(name);
	}

	@PostMapping
	public ChatRoomDto createChatRoom(@RequestBody ChatRoomDto chatRoomDto) {
		return chatRoomService.createChatRoom(chatRoomDto);
	}
}
