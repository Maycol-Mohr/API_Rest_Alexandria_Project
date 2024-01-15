package com.betrybe.alexandria2.repositories;

import com.betrybe.alexandria2.estities.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
}