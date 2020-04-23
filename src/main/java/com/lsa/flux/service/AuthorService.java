package com.lsa.flux.service;

import com.lsa.flux.repository.domain.Author;
import reactor.core.publisher.Mono;

public interface AuthorService {
    Mono<Author> createAuthor(Author author);
}
