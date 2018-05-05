package com.studio_projektowe.communicator.services;

import com.studio_projektowe.communicator.entities.Post;
import com.studio_projektowe.communicator.repositories.AppUserRepository;
import com.studio_projektowe.communicator.repositories.ConversationRepository;
import com.studio_projektowe.communicator.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class PostService {

    @Autowired
    PostRepository postRepository;

    public void addMessage(Post post){
        postRepository.save(post);
    }

}
