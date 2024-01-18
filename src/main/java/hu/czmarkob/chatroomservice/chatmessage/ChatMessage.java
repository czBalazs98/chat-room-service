package hu.czmarkob.chatroomservice.chatmessage;

import hu.czmarkob.chatroomservice.chatroom.ChatRoom;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Data
@EntityListeners(AuditingEntityListener.class)
public class ChatMessage {

	@Id
	@GeneratedValue
	private Long id;

	@CreatedDate
	@Column(nullable = false)
	private LocalDateTime created;

	@Column(nullable = false)
	private String message;

	@Column(nullable = false)
	private String sender;

	@ManyToOne(optional = false)
	private ChatRoom chatRoom;
}
