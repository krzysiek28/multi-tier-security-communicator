package com.studio_projektowe.communicator.services;

import com.studio_projektowe.communicator.repositories.AppUserRepository;
import com.studio_projektowe.communicator.repositories.ConversationRepository;
import org.springframework.stereotype.Service;

@Service
public class ConversationService {
    private final ConversationRepository conversationRepository;
    private final AppUserRepository appUserRepository;

    public ConversationService(ConversationRepository conversationRepository, AppUserRepository appUserRepository) {
        this.conversationRepository = conversationRepository;
        this.appUserRepository = appUserRepository;
    }
}
