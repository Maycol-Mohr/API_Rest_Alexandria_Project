package com.betrybe.alexandria2.repositories;

import com.betrybe.alexandria2.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

  //Page<Book> findAll(Pageable pageable);

  //Optional<Book> findByPublisher(Publisher publisher);
  //List<Book> findBooksByAuthors(Author author);
}