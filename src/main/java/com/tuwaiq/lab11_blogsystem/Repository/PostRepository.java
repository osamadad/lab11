package com.tuwaiq.lab11_blogsystem.Repository;

import com.tuwaiq.lab11_blogsystem.Model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post,Integer> {

    Post findPostsById(Integer id);

    @Query("select posts from Post posts where posts.userId=?1")
    List<Post> getPostsByUserId(Integer userID);

    @Query("select posts from Post posts where posts.publishDate<?1")
    List<Post> getPostsBeforeDate(LocalDateTime dateTime);
}
