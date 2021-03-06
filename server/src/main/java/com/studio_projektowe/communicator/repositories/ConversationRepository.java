package com.studio_projektowe.communicator.repositories;

import com.studio_projektowe.communicator.entities.Conversation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ConversationRepository extends JpaRepository<Conversation, Integer> {
    List<Conversation> findConversationsByUserId(String ownerId);
}
