package com.lsa.flux.service;

import com.lsa.flux.repository.ReactiveAuthorRepository;
import com.lsa.flux.repository.ReactiveBookRepository;
import com.lsa.flux.repository.domain.Book;
import com.lsa.flux.service.dto.BookResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private ReactiveBookRepository reactiveBookRepository;
    private ReactiveAuthorRepository reactiveAuthorRepository;
    final static List<Book> books = Arrays.asList(Book.builder().id("1").authorId("a").name("!").build(),
            Book.builder().id("2").authorId("b").name("@").build(),
            Book.builder().id("3").authorId("c").name("#").build(),
            Book.builder().id("4").authorId("d").name("$").build(),
            Book.builder().id("5").authorId("e").name("%").build(),
            Book.builder().id("6").authorId("f").name("^").build());
    @Autowired
    public void setReactiveBookRepository(ReactiveBookRepository reactiveBookRepository) {
        this.reactiveBookRepository = reactiveBookRepository;
    }

    @Autowired
    public void setReactiveAuthorRepository(ReactiveAuthorRepository reactiveAuthorRepository) {
        this.reactiveAuthorRepository = reactiveAuthorRepository;
    }

    @Override
    public Mono<Book> createBook(Book book, String authorId) {
        return reactiveAuthorRepository.findById(authorId).map(a -> book).flatMap(reactiveBookRepository::save)
                .switchIfEmpty(Mono.error(new Exception("Author not found")));
    }

    @Override
    public Mono<Book> findBook(String authorId, String bookId) {
        return reactiveBookRepository.findByIdAndAndAuthorId(bookId, authorId);
    }

    @Override
    public Mono<Book> findBook(String bookId) {
        return reactiveBookRepository.findById(bookId);
    }

    @Override
    public Flux<Book> getAllBooks() {
        return Flux.fromIterable(books).delayElements(Duration.ofSeconds(1));
    }

    @Override
    public Flux<BookDto> getAllBooksV2() {
        return Flux.from(reactiveBookRepository.findAll()).map(book -> BookDto.builder().id(book.getId()).build());
    }

    @Override
    public Mono<BookResult> listBooks(PageRequest req) {
        BookResult result = BookResult.builder()
                .data(new ArrayList<>())
                .page(0)
                .build();

        return null;
    }
}
