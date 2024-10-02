package com.example.demo.service;

import com.example.demo.model.Author;
import com.example.demo.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class BookService {

    private final List<Book> books = new ArrayList<>();

    public BookService(@Autowired AuthorService authorService) {
        Author author1  = authorService.getAuthor(1L);
        Author author2  = authorService.getAuthor(2L);
        Author author3  = authorService.getAuthor(3L);

        Book book1 = new Book(1L, "Book 1");
        book1.setAuthor(author1);
        Book book2 = new Book(2L, "Book 2");
        book2.setAuthor(author1);

        Book book3 = new Book(3L, "Book 3");
        book3.setAuthor(author2);

        Book book4 = new Book(4L, "Book 4");
        book4.setAuthor(author3);
        Book book5 = new Book(5L, "Book 5");
        book5.setAuthor(author3);

        books.add(book1);
        books.add(book2);
        books.add(book3);
        books.add(book4);
        books.add(book5);
    }

    public List<Book> getBooks() {
        return books;
    }

    public Book getBook(long id) {
        return books.stream()
                .filter(book -> book.getId() == id)
                .findFirst()
                .orElseThrow();
    }
}
