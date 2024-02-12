package hu.czmarkob.chatroomservice.chatmessage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@RequiredArgsConstructor
public class ChatMessageWebSocketHandler extends TextWebSocketHandler {

	private final Map<String, List<WebSocketSession>> sessions = new HashMap<>();

	@Autowired
	private ChatMessageService chatMessageService;

	@Autowired
	private ObjectMapper objectMapper;

	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		ChatMessageDto chatMessageDto = objectMapper.readValue(message.getPayload(), ChatMessageDto.class);
		chatMessageService.saveChatMessage(chatMessageDto);

		String chatRoomId = (String) session.getAttributes().get("chatRoomId");

		for (WebSocketSession webSocketSession : sessions.get(chatRoomId)) {
			webSocketSession.sendMessage(message);
		}
	}

	@Override
	public void afterConnectionEstablished(WebSocketSession session) {
		String chatRoomId = (String) session.getAttributes().get("chatRoomId");

		List<WebSocketSession> sessionList = sessions.computeIfAbsent(chatRoomId, k -> new ArrayList<>());
		sessionList.add(session);
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
		String chatRoomId = (String) session.getAttributes().get("chatRoomId");
		List<WebSocketSession> sessionList = sessions.get(chatRoomId);

		if (sessionList != null) {
			sessionList.remove(session);
			if ( sessionList.isEmpty() ) {
				sessions.remove(chatRoomId);
			}
		}
	}
}
