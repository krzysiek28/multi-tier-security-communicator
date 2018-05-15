package com.studio_projektowe.communicator.controllers;

import com.studio_projektowe.communicator.entities.Conversation;
import com.studio_projektowe.communicator.repositories.ConversationRepository;
import com.studio_projektowe.communicator.security.AuthorizationFilter;
import com.studio_projektowe.communicator.security.ResourceType;
import com.studio_projektowe.communicator.security.UnauthorizedException;
import com.studio_projektowe.communicator.services.ConversationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/conversation")
public class ConversationController {

    private final ConversationService conversationService;
    private final AuthorizationFilter authorizationFilter;

    @Autowired
    ConversationRepository conversationRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @PostMapping("")
    public void createConversation(@RequestBody Conversation conversation,  HttpServletResponse res) {
        conversationRepository.save(conversation);
    }

    public ConversationController(ConversationService conversationService, AuthorizationFilter authorizationFilter) {
        this.conversationService = conversationService;
        this.authorizationFilter = authorizationFilter;
    }

    @RequestMapping(value = "/{ownerId}")
    public List<Conversation> getAllConversations(@PathVariable String ownerId, @RequestHeader("Authorization") String token) throws UnauthorizedException {
        String id = jdbcTemplate.queryForObject("SELECT id from app_user where username=" + "\'" + ownerId + "\'" + ";", String.class);
        authorizationFilter.isAuthorizedTo(token, id, ResourceType.USER);
        return conversationService.getAllConversations();
    }

    @RequestMapping(value = "/messages/{name}")
    public Conversation getConversation(@PathVariable String name) throws UnauthorizedException {
        return conversationService.getConversation(name);
    }
}
