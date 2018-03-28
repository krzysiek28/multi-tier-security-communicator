package com.studio_projektowe.communicator.repositories;

import com.studio_projektowe.communicator.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Integer> {
}
