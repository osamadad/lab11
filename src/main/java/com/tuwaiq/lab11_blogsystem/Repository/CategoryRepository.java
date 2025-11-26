package com.tuwaiq.lab11_blogsystem.Repository;

import com.tuwaiq.lab11_blogsystem.Model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Integer> {

    Category findCategoryById(Integer id);
}
