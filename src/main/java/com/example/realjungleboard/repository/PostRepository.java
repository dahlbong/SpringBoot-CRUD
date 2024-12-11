package com.example.realjungleboard.repository;

import com.example.realjungleboard.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<PostEntity, Long> {
}
