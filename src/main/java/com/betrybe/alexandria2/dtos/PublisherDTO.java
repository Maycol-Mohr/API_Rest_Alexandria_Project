package com.betrybe.alexandria2.dtos;

import com.betrybe.alexandria2.estities.Publisher;

public record PublisherDTO(Long id, String name, String address) {

  public Publisher toPublisher() {
    return new Publisher(id, name, address);
  }
}