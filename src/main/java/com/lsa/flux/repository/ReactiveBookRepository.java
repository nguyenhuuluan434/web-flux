package com.lsa.flux.repository;

import com.lsa.flux.repository.domain.Book;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface ReactiveBookRepository extends ReactiveCrudRepository<Book, String> {

    Flux<Book> findByNameLike(final String name);

    Mono<Book> findByIdAndAndAuthorId(final String id, final String authorId);
}
