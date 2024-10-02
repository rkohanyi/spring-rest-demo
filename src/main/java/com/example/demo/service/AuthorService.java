package com.example.demo.service;

import com.example.demo.model.Author;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class AuthorService {

    private final List<Author> authors = new ArrayList<>();

    public AuthorService() {
        Author author1 = new Author(1L, "Author 1");
        Author author2 = new Author(2L, "Author 2");
        Author author3 = new Author(3L, "Author 3");

        authors.add(author1);
        authors.add(author2);
        authors.add(author3);
    }

    public Author getAuthor(long id) {
        return authors.stream()
                .filter(author -> author.getId() == id)
                .findFirst()
                .orElseThrow();
    }

    public Author create(String name) {
        Author author = new Author(getNextId(), name);
        authors.add(author);
        return author;
    }

    private long getNextId() {
        return authors.stream()
                .mapToLong(Author::getId)
                .max()
                .orElse(0L)  + 1L;
    }

    public List<Author> getAuthors() {
        return authors;
    }
}
