package com.tuwaiq.lab11_blogsystem.Controller;

import Api.ApiResponse;
import com.tuwaiq.lab11_blogsystem.Model.Post;
import com.tuwaiq.lab11_blogsystem.Service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/post")
public class PostController {

    private final PostService postService;

    @PostMapping("/add")
    public ResponseEntity<?> addPost(@RequestBody @Valid Post post, Errors errors){
        if (errors.hasErrors()){
            return ResponseEntity.status(400).body(new ApiResponse(errors.getFieldError().getDefaultMessage()));
        }else {
            String value= postService.addPost(post);
            switch (value){
                case "ok":
                    return ResponseEntity.status(200).body(new ApiResponse("The post have been added successfully"));
                case "user id error":
                    return ResponseEntity.status(400).body(new ApiResponse("There are no users with this id found"));
                case "category id error":
                    return ResponseEntity.status(400).body(new ApiResponse("There are no categories with this id found"));
                default:
                    return ResponseEntity.status(400).body(new ApiResponse("General error"));
            }
        }
    }

    @GetMapping("/get")
    public ResponseEntity<?> getPosts(){
        List<Post> posts=postService.getPosts();
        if (posts.isEmpty()){
            return ResponseEntity.status(400).body(new ApiResponse("There are no posts to show"));
        }else {
            return ResponseEntity.status(200).body(posts);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updatePost(@PathVariable Integer id, @RequestBody @Valid Post post, Errors errors){
        if (errors.hasErrors()){
            return ResponseEntity.status(400).body(new ApiResponse(errors.getFieldError().getDefaultMessage()));
        }else {
            String value= postService.updatePost(id,post);
            switch (value){
                case "ok":
                    return ResponseEntity.status(200).body(new ApiResponse("The post have been updated successfully"));
                case "user id error":
                    return ResponseEntity.status(400).body(new ApiResponse("There are no users with this id found"));
                case "category id error":
                    return ResponseEntity.status(400).body(new ApiResponse("There are no categories with this id found"));
                case "post id error":
                    return ResponseEntity.status(400).body(new ApiResponse("There are no post with this id found"));
                default:
                    return ResponseEntity.status(400).body(new ApiResponse("General error"));
            }
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deletePost(@PathVariable Integer id){
        if (postService.deletePost(id)){
            return ResponseEntity.status(200).body(new ApiResponse("The post have been deleted successfully"));
        }else {
            return ResponseEntity.status(400).body(new ApiResponse("There are no post with this id found"));
        }
    }

    @GetMapping("get-posts-by-user-id/{userId}")
    public ResponseEntity<?> getPostsByUserId(@PathVariable Integer userId){
        List<Post> posts= postService.getPostsByUserId(userId);
        if (posts.isEmpty()){
            return ResponseEntity.status(400).body(new ApiResponse("There are no post with this user id"));
        }else {
            return ResponseEntity.status(200).body(posts);
        }
    }
}
