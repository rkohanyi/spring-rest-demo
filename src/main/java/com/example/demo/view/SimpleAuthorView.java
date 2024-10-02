package com.example.demo.view;

import com.example.demo.model.Author;

public class SimpleAuthorView {

    private Long id;
    private String name;

    public SimpleAuthorView() {}

    public SimpleAuthorView(Author author) {
        this(author.getId(), author.getName());
    }

    public SimpleAuthorView(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
