package com.tuwaiq.lab11_blogsystem.Service;

import com.tuwaiq.lab11_blogsystem.Model.Comment;
import com.tuwaiq.lab11_blogsystem.Model.Post;
import com.tuwaiq.lab11_blogsystem.Model.User;
import com.tuwaiq.lab11_blogsystem.Repository.CategoryRepository;
import com.tuwaiq.lab11_blogsystem.Repository.CommentRepository;
import com.tuwaiq.lab11_blogsystem.Repository.PostRepository;
import com.tuwaiq.lab11_blogsystem.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    public String addComment(Comment comment){
        User user=userRepository.findUserById(comment.getUserId());
        Post post=postRepository.findPostsById(comment.getPostId());
        if (user!=null){
            if (post!=null){
                comment.setCommentDate(LocalDateTime.now());
                commentRepository.save(comment);
                return "ok";
            }else {
                return "post id error";
            }
        }else{
            return "user id error";
        }
    }

    public List<Comment> getComments(){
        return commentRepository.findAll();
    }

    public String updateComment(Integer id, Comment comment){
        Comment oldComment=commentRepository.findCommentById(id);
        User user=userRepository.findUserById(comment.getUserId());
        Post post=postRepository.findPostsById(comment.getPostId());
        if (oldComment!=null){
            if (user!=null){
                if (post!=null){
                    oldComment.setContent(comment.getContent());
                    oldComment.setPostId(comment.getPostId());
                    oldComment.setUserId(comment.getUserId());
                    commentRepository.save(oldComment);
                    return "ok";
                }else {
                    return "post id error";
                }
            }else {
                return "user id error";
            }
        }else {
            return "comment id error";
        }
    }
}
