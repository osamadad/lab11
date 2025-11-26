package com.tuwaiq.lab11_blogsystem.Controller;

import Api.ApiResponse;
import com.tuwaiq.lab11_blogsystem.Model.Category;
import com.tuwaiq.lab11_blogsystem.Service.CategoryService;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/category")
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping("/add")
    public ResponseEntity<?> addCategory(@RequestBody @Valid Category category, Errors errors){
        if (errors.hasErrors()){
            return ResponseEntity.status(400).body(new ApiResponse(errors.getFieldError().getDefaultMessage()));
        }else {
            categoryService.addCategory(category);
            return ResponseEntity.status(200).body(new ApiResponse("The category have been added successfully"));
        }
    }

    @GetMapping("/get")
    public ResponseEntity<?> getCategories(){
        List<Category> categories=categoryService.getCategories();
        if (categories.isEmpty()){
            return ResponseEntity.status(400).body(new ApiResponse("There are no categories to show"));
        }else {
            return ResponseEntity.status(200).body(categories);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateCategory(@PathVariable Integer id, Category category, Errors errors){
        if (errors.hasErrors()){
            return ResponseEntity.status(400).body(new ApiResponse(errors.getFieldError().getDefaultMessage()));
        }else {
            if (categoryService.updateCategory(id,category)){
                return ResponseEntity.status(200).body(new ApiResponse("The category have been updated successfully"));
            }else {
                return ResponseEntity.status(400).body(new ApiResponse("There are no categories with this id found"));
            }
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable Integer id){
        if (categoryService.deleteCategory(id)){
            return ResponseEntity.status(200).body(new ApiResponse("The category have been deleted successfully"));
        }else {
            return ResponseEntity.status(400).body(new ApiResponse("There are no categories with this id found"));
        }
    }

}
