package com.betrybe.alexandria2.dtos;

import com.betrybe.alexandria2.estities.Book;

public record BookDTO(Long id, String title, String genre) {

  public Book toBook() {
    return new Book(id, title, genre, null, null, null);
  }
}