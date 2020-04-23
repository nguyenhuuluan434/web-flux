package com.lsa.flux.controller;

import com.lsa.flux.repository.domain.Author;
import com.lsa.flux.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
public class AuthorController {

    private AuthorService authorService;

    @Autowired
    public void setAuthorService(AuthorService authorService) {
        this.authorService = authorService;
    }

    @PostMapping("/author")
    public Mono<Author> createAuthor(@Valid @RequestBody Author author) {
        return authorService.createAuthor(author);
    }
}
