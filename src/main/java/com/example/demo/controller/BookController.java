package com.example.demo.controller;

import com.example.demo.service.BookService;
import com.example.demo.view.SimpleBookView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/books")
class BookController {

    @Autowired
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<SimpleBookView> findAll() {
        return bookService.getBooks()
                .stream()
                .map(SimpleBookView::new)
                .toList();
    }

    @GetMapping(value = "/{id}")
    public SimpleBookView findById(@PathVariable("id") Long id) {
        return new SimpleBookView(bookService.getBook(id));
    }
}
