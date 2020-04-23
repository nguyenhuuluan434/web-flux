package com.lsa.flux.repository.domain;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;


@Getter
@Setter
@Document
@Builder
public class Book {

    @Id
    private String id;
    @NotBlank
    private String authorId;
    @NotBlank
    private String name;
}
