package com.lsa.flux.service;

import com.lsa.flux.repository.domain.Book;
import com.lsa.flux.service.dto.BookResult;
import org.springframework.data.domain.PageRequest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BookService {
    Mono<Book> createBook(Book book, String authorId);

    Mono<Book> findBook(String authorId, String bookId);

    Mono<Book> findBook(String bookId);

    Flux<Book> getAllBooks();

    Flux<BookDto> getAllBooksV2();

    Mono<BookResult> listBooks(PageRequest req);
}
