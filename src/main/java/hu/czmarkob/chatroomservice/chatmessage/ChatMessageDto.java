package hu.czmarkob.chatroomservice.chatmessage;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ChatMessageDto {

	@JsonProperty("id")
	private Long id;

	@JsonProperty("created")
	private LocalDateTime created;

	@JsonProperty("message")
	private String message;

	@JsonProperty("sender")
	private String sender;

	@JsonProperty("chatRoomId")
	private Long chatRoomId;
}
