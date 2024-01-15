package com.betrybe.alexandria2.repositories;

import com.betrybe.alexandria2.estities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
}