package hu.czmarkob.chatroomservice.chatroom;

import java.util.List;

import lombok.Data;

@Data
public class ChatRoomDto {

	private Long id;

	private String name;

	private String rules;

	private String description;

	private List<String> tags;

	private String imageSrc;
}
