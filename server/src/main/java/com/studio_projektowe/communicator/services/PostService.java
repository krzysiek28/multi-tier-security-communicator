package com.studio_projektowe.communicator.services;

import com.studio_projektowe.communicator.entities.Post;
import com.studio_projektowe.communicator.repositories.AppUserRepository;
import com.studio_projektowe.communicator.repositories.PostRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostService {
    private final PostRepository postRepository;
    private final AppUserRepository appUserRepository;

    public PostService(PostRepository postRepository, AppUserRepository appUserRepository) {
        this.postRepository = postRepository;
        this.appUserRepository = appUserRepository;
    }

    public void addMessage(Post post){
        postRepository.save(post);
    }

    public List<Post> getAllPosts(String conversationId) {
        List<Post> allPosts = new ArrayList<>();
        postRepository.findPostsByConversationId(conversationId)
                .forEach(allPosts::add);
        return allPosts;
    }


}
