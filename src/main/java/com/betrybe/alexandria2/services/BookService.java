package com.betrybe.alexandria2.services;

import com.betrybe.alexandria2.estities.Book;
import com.betrybe.alexandria2.estities.BookDetail;
import com.betrybe.alexandria2.repositories.BookDetailsRepository;
import com.betrybe.alexandria2.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

  @Autowired
  private BookRepository bookRepository;

  @Autowired
  private BookDetailsRepository bookDetailsRepository;

  public Book insertBook(Book book) {
    return bookRepository.save(book);
  }

  public Optional<Book> updateBook(Long id, Book book) {

    Optional<Book> optionalBook = bookRepository.findById(id);

    if(optionalBook.isPresent()) {
      Book bookFromDB = optionalBook.get();
      bookFromDB.setTitle(book.getTitle());
      bookFromDB.setGenre(book.getGenre());

      Book updatedBook = bookRepository.save(bookFromDB);
      return Optional.of(updatedBook);

    }

    return optionalBook;
  }

  public Optional<Book> removeBookById(Long id) {

    Optional<Book> bookOptional = bookRepository.findById(id);

    if(bookOptional.isPresent()) {
      bookRepository.deleteById(id);
    }

    return bookOptional;
  }

  public Optional<Book> getBookById(Long id) {
    return bookRepository.findById(id);
  }

  public List<Book> getAllBooks() {
    return bookRepository.findAll();
  }

  public BookDetail insertBookDetails(BookDetail bookDetail) {
    return bookDetailsRepository.save(bookDetail);
  }

  public Optional<BookDetail> updateBookDetails(Long id, BookDetail bookDetail) {

    Optional<BookDetail> optionalBookDetail = bookDetailsRepository.findById(id);

    if(optionalBookDetail.isPresent()) {
      BookDetail bookDetailsFromDB = optionalBookDetail.get();
      bookDetailsFromDB.setSummary(bookDetail.getSummary());
      bookDetailsFromDB.setPageCount(bookDetail.getPageCount());
      bookDetailsFromDB.setYear(bookDetail.getYear());
      bookDetailsFromDB.setIsbn(bookDetail.getIsbn());

      BookDetail updatedBookDetail = bookDetailsRepository.save(bookDetailsFromDB);
      return Optional.of(updatedBookDetail);

    }

    return optionalBookDetail;
  }

  public Optional<BookDetail> removeBookDetailById(Long id) {

    Optional<BookDetail> bookOptionalDetail = bookDetailsRepository.findById(id);

    if(bookOptionalDetail.isPresent()) {
      bookDetailsRepository.deleteById(id);
    }

    return bookOptionalDetail;
  }

  public Optional<BookDetail> getBookDetailById(Long id) {
    return bookDetailsRepository.findById(id);
  }
}