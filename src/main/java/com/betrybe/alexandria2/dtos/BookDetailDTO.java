package com.betrybe.alexandria2.dtos;

import com.betrybe.alexandria2.estities.BookDetail;
import com.fasterxml.jackson.annotation.JsonProperty;

public record BookDetailDTO(
        Long id,

        String summary,

        @JsonProperty("page_count")
        Integer pageCount,

        String year,

        String isbn) {

  public BookDetail toBookDetail() {
    return new BookDetail(id, summary, pageCount, year, isbn);
  }
}
