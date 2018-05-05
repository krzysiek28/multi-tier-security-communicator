package com.studio_projektowe.communicator.services;

import com.studio_projektowe.communicator.entities.Conversation;
import com.studio_projektowe.communicator.repositories.AppUserRepository;
import com.studio_projektowe.communicator.repositories.ConversationRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ConversationService {
    private final ConversationRepository conversationRepository;
    private final AppUserRepository appUserRepository;

    public ConversationService(ConversationRepository conversationRepository, AppUserRepository appUserRepository) {
        this.conversationRepository = conversationRepository;
        this.appUserRepository = appUserRepository;
    }

    public List<Conversation> getAllConversations(Integer ownerId) {
        if (!conversationRepository.exists(ownerId))
            throw new IllegalArgumentException("Niepoprawne id użytkownika!");

        List<Conversation> allConversations = new ArrayList<>();
        conversationRepository.findByUserId(ownerId)
                .forEach(allConversations::add);
        return allConversations;
    }
}
