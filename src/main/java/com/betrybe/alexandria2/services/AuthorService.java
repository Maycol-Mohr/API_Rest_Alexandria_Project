package com.betrybe.alexandria2.services;

import com.betrybe.alexandria2.entities.Author;
import com.betrybe.alexandria2.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {

  @Autowired
  private AuthorRepository authorRepository;

  @Transactional
  public Author insertAuthor(Author author) {
    return authorRepository.save(author);
  }

  @Transactional
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

  @Transactional
  public Optional<Author> removeAuthorById(Long id) {

    Optional<Author> authorOptional = authorRepository.findById(id);

    if(authorOptional.isPresent()) {
      authorRepository.deleteById(id);
    }

    return authorOptional;
  }

  @Transactional(readOnly = true)
  public Optional<Author> getAuthorById(Long id) {
    return authorRepository.findById(id);
  }

  @Transactional(readOnly = true)
  public List<Author> getAllAuthors() {
    return authorRepository.findAll();
  }
}