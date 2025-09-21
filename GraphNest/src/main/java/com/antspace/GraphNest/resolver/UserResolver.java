package com.antspace.GraphNest.resolver;

import com.antspace.GraphNest.models.User;
import com.antspace.GraphNest.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class UserResolver {
    private final UserService userService;

    @QueryMapping
    public List<User> users(@Argument String search) {
        return userService.getUsers(search);
    }

    @MutationMapping
    public User addUser(@Argument String name, @Argument String email, @Argument String role) {
        return userService.addUser(name, email, role);
    }

    @MutationMapping
    public User updateUser(@Argument Long id, @Argument String name, @Argument String email, @Argument String role) {
        return userService.updateUser(id, name, email, role);
    }

    @MutationMapping
    public boolean deleteUser(@Argument Long id) {
        return userService.deleteUser(id);
    }
}
