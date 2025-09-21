package com.antspace.GraphNest.service;

import com.antspace.GraphNest.models.Category;
import com.antspace.GraphNest.models.Product;
import com.antspace.GraphNest.repository.CategoryRepository;
import com.antspace.GraphNest.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public List<Product> getProducts(String search) {
        if (search == null || search.isBlank()) return productRepository.findAll();
        return productRepository.findByNameContainingIgnoreCase(search);
    }

    public Product addProduct(String name, Double price, Long categoryId) {
        Category category = null;
        if (categoryId != null) {
            category = categoryRepository.findById(categoryId)
                    .orElseThrow(() -> new RuntimeException("Category not found: " + categoryId));
        }
        Product p = new Product(null, name, price, category);
        return productRepository.save(p);
    }

    public Product updateProduct(Long id, String name, Double price, Long categoryId) {
        return productRepository.findById(id).map(prod -> {
            if (name != null) prod.setName(name);
            if (price != null) prod.setPrice(price);
            if (categoryId != null) {
                Category category = categoryRepository.findById(categoryId)
                        .orElseThrow(() -> new RuntimeException("Category not found: " + categoryId));
                prod.setCategory(category);
            }
            return productRepository.save(prod);
        }).orElseThrow(() -> new RuntimeException("Product not found: " + id));
    }

    public boolean deleteProduct(Long id) {
        if (!productRepository.existsById(id)) return false;
        productRepository.deleteById(id);
        return true;
    }
}
