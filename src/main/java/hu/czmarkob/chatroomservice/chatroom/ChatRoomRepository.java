package hu.czmarkob.chatroomservice.chatroom;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {

	List<ChatRoom> findByNameContains(String name);
}
