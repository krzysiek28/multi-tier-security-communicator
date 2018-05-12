package com.studio_projektowe.communicator.controllers;

import com.studio_projektowe.communicator.entities.Post;
import com.studio_projektowe.communicator.repositories.PostRepository;
import com.studio_projektowe.communicator.security.AuthorizationFilter;
import com.studio_projektowe.communicator.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/post")
public class PostController {

    private final PostService postService;
    private final AuthorizationFilter authorizationFilter;

    @Autowired
    PostRepository postRepository;

    public PostController(PostService postService, AuthorizationFilter authorizationFilter) {
        this.postService = postService;
        this.authorizationFilter = authorizationFilter;
    }

    @RequestMapping("/newMessage")
    @MessageMapping("/newMessage")
    @SendTo("/topic/newMessage")
    public Post postMessage(Post post){
        postService.addMessage(post);
        return post;
    }

    @RequestMapping(value = "/{conversationName}")
    public List<Post> getAllPosts(@PathVariable("conversationName") String conversationName){
        return postService.getAllPosts(conversationName);
    }

    @PostMapping(value = "/newPost")
    public void createPost(@RequestBody Post post,  HttpServletResponse res) {
        postRepository.save(post);
    }

}
