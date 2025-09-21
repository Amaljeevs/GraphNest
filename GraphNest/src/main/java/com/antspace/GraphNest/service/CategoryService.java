package com.antspace.GraphNest.service;

import com.antspace.GraphNest.models.Category;
import com.antspace.GraphNest.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public List<Category> getCategories(String search) {
        if (search == null || search.isBlank()) return categoryRepository.findAll();
        return categoryRepository.findByNameContainingIgnoreCase(search);
    }

    public Category addCategory(String name) {
        return categoryRepository.save(new Category(null, name));
    }

    public Category updateCategory(Long id, String name) {
        return categoryRepository.findById(id).map(cat -> {
            if (name != null) cat.setName(name);
            return categoryRepository.save(cat);
        }).orElseThrow(() -> new RuntimeException("Category not found: " + id));
    }

    public boolean deleteCategory(Long id) {
        if (!categoryRepository.existsById(id)) return false;
        categoryRepository.deleteById(id);
        return true;
    }
}
