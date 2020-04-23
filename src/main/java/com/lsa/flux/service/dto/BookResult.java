package com.lsa.flux.service.dto;

import com.lsa.flux.repository.domain.Book;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Setter
@Getter
public class BookResult {
    private List<Book> data;
    private long page;
}
