package com.tuwaiq.lab11_blogsystem.Service;

import com.tuwaiq.lab11_blogsystem.Model.Category;
import com.tuwaiq.lab11_blogsystem.Repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public void addCategory(Category category){
        categoryRepository.save(category);
    }

    public List<Category> getCategories(){
        return categoryRepository.findAll();
    }

    public Boolean updateCategory(Integer id, Category category){
        Category oldCategory=categoryRepository.findCategoryById(id);
        if (oldCategory==null){
            return false;
        }else {
            oldCategory.setName(category.getName());
            return true;
        }
    }

    public Boolean deleteCategory(Integer id){
        Category category=categoryRepository.findCategoryById(id);
        if (category==null){
            return false;
        }else {
            categoryRepository.delete(category);
            return true;
        }
    }
}
