package hu.czmarkob.chatroomservice.chatroom;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.Data;

@Entity
@Data
public class ChatRoom {

	@Id
	@GeneratedValue
	private Long id;

	@Column(nullable = false)
	private String name;

	private String rules;

	private String description;

	@ElementCollection
	private List<String> tags;

	@Lob
	@Column(length = 512)
	private String imageSrc;
}
