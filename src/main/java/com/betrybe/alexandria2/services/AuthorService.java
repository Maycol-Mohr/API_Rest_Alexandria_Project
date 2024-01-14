package com.betrybe.alexandria2.services;

import com.betrybe.alexandria2.estities.Author;
import com.betrybe.alexandria2.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {

  @Autowired
  private AuthorRepository authorRepository;

  public Author insertAuthor(Author author) {
    return authorRepository.save(author);
  }

  public Optional<Author> updateAuthor(Long id, Author author) {

    Optional<Author> optionalAuthor = authorRepository.findById(id);

    if(optionalAuthor.isPresent()) {
      Author authorFromDB = optionalAuthor.get();
      authorFromDB.setName(author.getName());
      authorFromDB.setNationality(author.getNationality());

      Author updatedAuthor = authorRepository.save(authorFromDB);
      return Optional.of(updatedAuthor);

    }

    return optionalAuthor;
  }

  public Optional<Author> removeAuthorById(Long id) {

    Optional<Author> authorOptional = authorRepository.findById(id);

    if(authorOptional.isPresent()) {
      authorRepository.deleteById(id);
    }

    return authorOptional;
  }

  public Optional<Author> getAuthorById(Long id) {
    return authorRepository.findById(id);
  }

  public List<Author> getAllAuthors() {
    return authorRepository.findAll();
  }
}