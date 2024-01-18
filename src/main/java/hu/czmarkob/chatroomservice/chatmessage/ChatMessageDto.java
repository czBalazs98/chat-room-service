package hu.czmarkob.chatroomservice.chatmessage;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ChatMessageDto {

	private Long id;

	private LocalDateTime created;

	private String message;

	private String sender;

	private Long chatRoomId;
}
