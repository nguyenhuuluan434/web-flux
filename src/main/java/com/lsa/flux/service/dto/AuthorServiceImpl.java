package com.lsa.flux.service.dto;

import com.lsa.flux.repository.ReactiveAuthorRepository;
import com.lsa.flux.repository.domain.Author;
import com.lsa.flux.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class AuthorServiceImpl implements AuthorService {

    private ReactiveAuthorRepository reactiveAuthorRepository;

    @Autowired
    public void setReactiveAuthorRepository(ReactiveAuthorRepository reactiveAuthorRepository) {
        this.reactiveAuthorRepository = reactiveAuthorRepository;
    }

    @Override
    public Mono<Author> createAuthor(Author author) {
        return reactiveAuthorRepository.save(author);
    }
}
