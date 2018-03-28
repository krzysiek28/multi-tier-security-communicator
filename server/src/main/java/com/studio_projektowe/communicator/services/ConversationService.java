package com.studio_projektowe.communicator.services;

import com.studio_projektowe.communicator.repositories.AppUserRepository;
import com.studio_projektowe.communicator.repositories.ConversationRepository;
import org.springframework.stereotype.Service;

@Service
public class ConversationService {
    private ConversationRepository conversationRepository;
    private AppUserRepository appUserRepository;
}
