package com.betrybe.alexandria2.dtos;

import com.betrybe.alexandria2.estities.Author;

public record AuthorDTO(Long id, String name, String nationality) {

  public Author toAuthor() {
    return new Author(id, name, nationality);
  }

}
