package com.lsa.flux.controller;

import com.lsa.flux.repository.domain.Book;
import com.lsa.flux.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
@Slf4j
public class BookController {
    private BookService bookService;

    @Autowired
    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("/book")
    public Mono<Book> createBook(@Valid @RequestBody Book book) {
        return bookService.createBook(book, book.getAuthorId());
    }

    @GetMapping("/book/{id}")
    public Mono<Book> getBook(@PathVariable(value = "id") String bookId) {
        return bookService.findBook(bookId);
    }

    @GetMapping(path = "/book", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Book> getBooks() {
        log.info("test");
        return bookService.getAllBooks();
    }
}
