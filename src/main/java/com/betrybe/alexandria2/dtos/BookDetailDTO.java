package com.betrybe.alexandria2.dtos;

import com.betrybe.alexandria2.entities.Book;
import com.betrybe.alexandria2.entities.BookDetail;
import com.fasterxml.jackson.annotation.JsonProperty;

public record BookDetailDTO(
        Long id,

        String summary,

        @JsonProperty("page_count")
        Integer pageCount,

        String year,

        String isbn,

        Book book) {

  public BookDetail toBookDetail() {
    return new BookDetail(id, summary, pageCount, year, isbn, null);
  }
}