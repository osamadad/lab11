package com.tuwaiq.lab11_blogsystem.Service;

import com.tuwaiq.lab11_blogsystem.Model.Category;
import com.tuwaiq.lab11_blogsystem.Model.Post;
import com.tuwaiq.lab11_blogsystem.Model.User;
import com.tuwaiq.lab11_blogsystem.Repository.CategoryRepository;
import com.tuwaiq.lab11_blogsystem.Repository.PostRepository;
import com.tuwaiq.lab11_blogsystem.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    public String addPost(Post post){
        User user=userRepository.findUserById(post.getUserId());
        Category category=categoryRepository.findCategoryById(post.getCategoryId());
        if (user!=null){
            if (category!=null){
                post.setPublishDate(LocalDateTime.now());
                postRepository.save(post);
                return "ok";
            }else {
                return "category id error";
            }
        }else {
            return "user id error";
        }
    }

    public List<Post> getPosts(){
        return postRepository.findAll();
    }

    public String updatePost(Integer id, Post post){
        Post oldPost=postRepository.findPostsById(id);
        User user=userRepository.findUserById(post.getUserId());
        Category category=categoryRepository.findCategoryById(post.getCategoryId());
        if (oldPost!=null){
            if (user!=null){
                if (category!=null){
                    oldPost.setTitle(post.getTitle());
                    oldPost.setContent(post.getContent());
                    oldPost.setUserId(post.getUserId());
                    oldPost.setCategoryId(post.getCategoryId());
                    postRepository.save(oldPost);
                    return "ok";
                }else {
                    return "category id error";
                }
            }else {
                return "user id error";
            }
        }else {
            return "post id error";
        }
    }

    public Boolean deletePost(Integer id){
        Post post=postRepository.findPostsById(id);
        if (post==null){
            return false;
        }else {
            postRepository.delete(post);
            return true;
        }
    }

    public List<Post> getPostsByUserId(Integer userId){
        return postRepository.getPostsByUserId(userId);
    }
}
