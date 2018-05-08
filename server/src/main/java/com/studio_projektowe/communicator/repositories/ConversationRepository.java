package com.studio_projektowe.communicator.repositories;

import com.studio_projektowe.communicator.entities.Conversation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConversationRepository extends JpaRepository<Conversation, Integer> {
}
