package com.studio_projektowe.communicator.controllers;

import com.studio_projektowe.communicator.entities.Conversation;
import com.studio_projektowe.communicator.repositories.ConversationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/conversation")
public class ConversationController {

    @Autowired
    ConversationRepository conversationRepository;

    @PostMapping("")
    public void createConversation(@RequestBody  Conversation conversation,  HttpServletResponse res) {
        conversationRepository.save(conversation);
    }
}
