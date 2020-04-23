package com.lsa.flux.repository;

import com.lsa.flux.repository.domain.Author;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReactiveAuthorRepository extends ReactiveMongoRepository<Author, String> {
}
