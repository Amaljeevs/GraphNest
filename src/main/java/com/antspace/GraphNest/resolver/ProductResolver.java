package com.antspace.GraphNest.resolver;

import com.antspace.GraphNest.models.Product;
import com.antspace.GraphNest.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ProductResolver {
    private final ProductService productService;

    @QueryMapping
    public List<Product> products(@Argument String search) {
        return productService.getProducts(search);
    }

    @MutationMapping
    public Product addProduct(@Argument String name, @Argument Double price, @Argument Long categoryId) {
        return productService.addProduct(name, price, categoryId);
    }

    @MutationMapping
    public Product updateProduct(@Argument Long id, @Argument String name, @Argument Double price, @Argument Long categoryId) {
        return productService.updateProduct(id, name, price, categoryId);
    }

    @MutationMapping
    public boolean deleteProduct(@Argument Long id) {
        return productService.deleteProduct(id);
    }
}
