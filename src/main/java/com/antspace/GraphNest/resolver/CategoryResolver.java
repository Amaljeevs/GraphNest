package com.antspace.GraphNest.resolver;

import com.antspace.GraphNest.models.Category;
import com.antspace.GraphNest.service.CategoryService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class CategoryResolver {

    private final CategoryService categoryService;

    @QueryMapping
    public List<Category> categories(@Argument String search) {
        return categoryService.getCategories(search);
    }

    @MutationMapping
    public Category addCategory(@Argument String name) {
        return categoryService.addCategory(name);
    }

    @MutationMapping
    public Category updateCategory(@Argument Long id, @Argument String name) {
        return categoryService.updateCategory(id, name);
    }

    @MutationMapping
    public boolean deleteCategory(@Argument Long id) {
        return categoryService.deleteCategory(id);
    }
}
