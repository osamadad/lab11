package com.tuwaiq.lab11_blogsystem.Controller;

import Api.ApiResponse;
import com.tuwaiq.lab11_blogsystem.Model.Comment;
import com.tuwaiq.lab11_blogsystem.Model.Post;
import com.tuwaiq.lab11_blogsystem.Service.CommentService;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.service.annotation.DeleteExchange;

import java.util.List;

@RestController
@RequestMapping("/api/v1/comment")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/add")
    public ResponseEntity<?> addComment(@RequestBody @Valid Comment comment, Errors errors){
        if (errors.hasErrors()){
            return ResponseEntity.status(400).body(new ApiResponse(errors.getFieldError().getDefaultMessage()));
        }else {
            String value= commentService.addComment(comment);
            switch (value){
                case "ok":
                    return ResponseEntity.status(200).body(new ApiResponse("The comment have been added successfully"));
                case "post id error":
                    return ResponseEntity.status(400).body(new ApiResponse("There are no posts with this id found"));
                case "user id error":
                    return ResponseEntity.status(400).body(new ApiResponse("There are no users with this id found"));
                default:
                    return ResponseEntity.status(400).body(new ApiResponse("General error"));
            }
        }
    }

    @GetMapping("/get")
    public ResponseEntity<?> getComment(){
        List<Comment> comments= commentService.getComments();
        if (comments.isEmpty()){
            return ResponseEntity.status(400).body(new ApiResponse("There are no comments to show"));
        }else {
            return ResponseEntity.status(200).body(comments);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateComment(@PathVariable Integer id, @RequestBody @Valid Comment comment, Errors errors){
        if (errors.hasErrors()){
            return ResponseEntity.status(400).body(new ApiResponse(errors.getFieldError().getDefaultMessage()));
        }else {
            String value= commentService.updateComment(id,comment);
            switch (value){
                case "ok":
                    return ResponseEntity.status(200).body(new ApiResponse("The comment have been updated successfully"));
                case "post id error":
                    return ResponseEntity.status(400).body(new ApiResponse("There are no posts with this id found"));
                case "user id error":
                    return ResponseEntity.status(400).body(new ApiResponse("There are no users with this id found"));
                case "comment id found":
                    return ResponseEntity.status(400).body(new ApiResponse("There are no comments with this id found"));
                default:
                    return ResponseEntity.status(400).body(new ApiResponse("General error"));
            }
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteComment(@PathVariable Integer id){
        if (commentService.deleteComment(id)){
            return ResponseEntity.status(200).body(new ApiResponse("The comment have been deleted successfully"));
        }else {
            return ResponseEntity.status(400).body(new ApiResponse("There are no comments with this id found"));
        }
    }
}
