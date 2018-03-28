package com.studio_projektowe.communicator.services;

import com.studio_projektowe.communicator.repositories.AppUserRepository;
import com.studio_projektowe.communicator.repositories.ConversationRepository;
import com.studio_projektowe.communicator.repositories.PostRepository;
import org.springframework.stereotype.Service;

@Service
public class PostService {
    private PostRepository postRepository;
    private ConversationRepository conversationRepository;
    private AppUserRepository appUserRepository;
}
