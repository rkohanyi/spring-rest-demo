package com.example.demo.controller;

import com.example.demo.model.Author;
import com.example.demo.service.AuthorService;
import com.example.demo.view.CreateAuthorView;
import com.example.demo.view.SimpleAuthorView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/authors")
class AuthorController {

    @Autowired
    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    public List<Author> findAll() {
        return authorService.getAuthors();
    }

    @GetMapping(value = "/{id}")
    public Author findById(@PathVariable("id") Long id) {
        return authorService.getAuthor(id);
    }

    @PostMapping
    public SimpleAuthorView create(@RequestBody CreateAuthorView createAuthor) {
        return new SimpleAuthorView(authorService.create(createAuthor.getName()));
    }
}
