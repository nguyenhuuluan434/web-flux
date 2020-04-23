package com.lsa.flux.repository.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;

@Document
@Getter
@Setter
public class Author {
    @Id
    private String id;
    @NotNull
    private String name;
}
