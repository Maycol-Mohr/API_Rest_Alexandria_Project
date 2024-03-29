package com.betrybe.alexandria2.dtos;

import com.betrybe.alexandria2.entities.Book;
import com.betrybe.alexandria2.entities.Publisher;

import java.util.List;

public record PublisherDTO(Long id, String name, String address, List<Book> books) {

  public Publisher toPublisher() {
    return new Publisher(id, name, address);
  }

  public Publisher toPublisher2() {
    return new Publisher(id, name, address, books);
  }
}