package com.betrybe.alexandria2.repositories;

import com.betrybe.alexandria2.entities.BookDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookDetailsRepository extends JpaRepository<BookDetail, Long> {
}