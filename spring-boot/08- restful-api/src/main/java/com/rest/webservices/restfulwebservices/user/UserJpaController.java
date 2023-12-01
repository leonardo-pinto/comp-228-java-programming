package com.rest.webservices.restfulwebservices.user;


import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class UserJpaController {

    private UserRepository repository;
    private PostRepository postRepository;

    public UserJpaController(
            UserRepository repository, PostRepository postRepository) {
        this.repository = repository;
        this.postRepository = postRepository;
    }

    @GetMapping("/users")
    public List<User> retrieveAllUsers() {

        return repository.findAll();
    }

    @GetMapping("/users/{id}")
    public Optional<User> retrieveUserById(@PathVariable int id) {
        Optional<User> user = repository.findById(id);

        if (user.isEmpty()) {
            throw new UserNotFoundException("id: " + id);
        }

        return user;
    }

    @GetMapping("/users/{id}/posts")

    public List<Post> retrievePostByUser(@PathVariable int id) {
        Optional<User> user = repository.findById(id);

        if (user.isEmpty()) {
            throw new UserNotFoundException("id: " + id);
        }

        return user.get().getPosts();
    }

    @PostMapping("/users/{id}/posts")
    public ResponseEntity<Object> createPostForUser(@PathVariable int id, @Valid @RequestBody Post post) {
        Optional<User> user = repository.findById(id);

        if (user.isEmpty()) {
            throw new UserNotFoundException("id: " + id);
        }

        post.setUser(user.get());

        Post createdPost = postRepository.save(post);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdPost.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

//    @PostMapping("/users")
//    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
//        User createdUser = service.save(user);
//        URI location = ServletUriComponentsBuilder
//                .fromCurrentRequest()
//                .path("/{id}")
//                .buildAndExpand(createdUser.getId())
//                .toUri();
//        return ResponseEntity.created(location).build();
//    }
//
//    @DeleteMapping("/users/{id}")
//    public void deleteUser(@PathVariable int id) {
//       service.delete(id);
//    }
}
