package com.betrybe.alexandria2.services;

import com.betrybe.alexandria2.dtos.BookDTO;
import com.betrybe.alexandria2.entities.Author;
import com.betrybe.alexandria2.entities.Book;
import com.betrybe.alexandria2.entities.BookDetail;
import com.betrybe.alexandria2.entities.Publisher;
import com.betrybe.alexandria2.repositories.AuthorRepository;
import com.betrybe.alexandria2.repositories.BookDetailsRepository;
import com.betrybe.alexandria2.repositories.BookRepository;
import com.betrybe.alexandria2.repositories.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class BookService {

  @Autowired
  private BookRepository bookRepository;

  //@Autowired
  private BookDetailsRepository bookDetailRepository;

  //@Autowired
  private final PublisherRepository publisherRepository;

  private AuthorRepository authorRepository;

  //@Autowired
  public BookService(BookRepository bookRepository, BookDetailsRepository bookDetailRepository, PublisherRepository publisherRepository, AuthorRepository authorRepository) {
    this.bookRepository = bookRepository;
    this.bookDetailRepository = bookDetailRepository;
    this.publisherRepository = publisherRepository;
    this.authorRepository = authorRepository;
  }

  @Transactional
  public Book insertBook(Book book) {
    return bookRepository.save(book);
  }

  @Transactional
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

  @Transactional
  public Optional<Book> removeBookById(Long id) {

    Optional<Book> bookOptional = bookRepository.findById(id);

    if(bookOptional.isPresent()) {
      bookRepository.deleteById(id);
    }

    return bookOptional;
  }

  @Transactional(readOnly = true)
  public Optional<Book> getBookById(Long id) {
    return bookRepository.findById(id);
  }

  //public List<BookDTO> getAllBooks(int pageNumber, int pageSize) {
   // List<BookDTO> booksDTOList = new ArrayList<>();

    //Pageable pageable = PageRequest.of(pageNumber, pageSize);
    //Page<Book> books = bookRepository.findAll(pageable);

    //books.getTotalElements();

    //books.getTotalPages();

    //int totalPages = books.getTotalPages(); // Obtém o número total de páginas
    //long totalElements = books.getTotalElements(); // Obtém o número total de elementos

    //books.forEach((book) -> {
     // BookDTO bookDTO = new BookDTO(book.getId(), book.getTitle(), book.getGenre());
     // booksDTOList.add(bookDTO);
    //});

   // return booksDTOList;
 // }

  //example: http://localhost:8080/books?page=0&sort=title,asc
  @Transactional(readOnly = true)
  public Page<BookDTO> getAllBooks(Pageable pageable) {
    Page<Book> list = bookRepository.findAll(pageable);
    return list.map(x -> new BookDTO(x.getId(), x.getTitle(), x.getGenre()));
  }

  @Transactional
  public Optional<BookDetail> insertBookDetail(Long bookId, BookDetail bookDetail) {
    Optional<Book> optionalBook = bookRepository.findById(bookId);

    if (optionalBook.isPresent()) {
      Book book = optionalBook.get();
      bookDetail.setBook(book);
      BookDetail newBookDetail = bookDetailRepository.save(bookDetail);
      return Optional.of(newBookDetail);
    }

    return Optional.empty();
  }

  @Transactional
  public Optional<BookDetail> updateBookDetail(Long bookId, BookDetail bookDetail) {
    Optional<Book> optionalBook = bookRepository.findById(bookId);

    if (optionalBook.isPresent()) {
      Book book = optionalBook.get();
      BookDetail bookDetailFromDB = book.getDetails();
      bookDetailFromDB.setSummary(bookDetail.getSummary());
      bookDetailFromDB.setPageCount(bookDetail.getPageCount());
      bookDetailFromDB.setYear(bookDetail.getYear());
      bookDetailFromDB.setIsbn(bookDetail.getIsbn());

      BookDetail updatedBookDetail = bookDetailRepository.save(bookDetailFromDB);
      return Optional.of(updatedBookDetail);

    }

    return Optional.empty();
  }

  @Transactional
  public Optional<BookDetail> removeBookDetailById(Long bookId) {
    Optional<Book> optionalBook = bookRepository.findById(bookId);

    if (optionalBook.isPresent()) {
      Book book = optionalBook.get();
      Optional<BookDetail> optionalBookDetail = bookDetailRepository.findById(book.getDetails().getId());

      if (optionalBookDetail.isPresent()) {
        book.setDetails(null);
        BookDetail bookDetail = optionalBookDetail.get();
        bookDetailRepository.deleteById(bookDetail.getId());

        return Optional.of(bookDetail);
      }
    }

    return Optional.empty();
  }

  @Transactional(readOnly = true)
  public Optional<BookDetail> getBookDetailById(Long id) {
    return bookDetailRepository.findById(id);
  }

  @Transactional
  public Optional<Book> setPublisher(Long bookId, Long publisherId) {
    Optional<Book> optionalBook = bookRepository.findById(bookId);
    if(optionalBook.isEmpty()){
      return Optional.empty();
    }

    Optional<Publisher> optionalPublisher = publisherRepository.findById(publisherId);
    if(optionalPublisher.isEmpty()){
      return Optional.empty();
    }

    Book book = optionalBook.get();
    Publisher publisher = optionalPublisher.get();

    book.setPublisher(publisher);
    Book updatedBook = bookRepository.save(book);

    return Optional.of(updatedBook);
  }

  @Transactional
  public Optional<Book> removePublisher(Long bookId) {
    Optional<Book> optionalBook = bookRepository.findById(bookId);
    if(optionalBook.isEmpty()){
      return Optional.empty();
    }

    Book book = optionalBook.get();
    book.setPublisher(null);

    Book newBook = bookRepository.save(book);
    return Optional.of(newBook);
  }

  @Transactional
  public Optional<Book> setAuthor(Long bookId, Long authorId) {

    Optional<Book> optionalBook = bookRepository.findById(bookId);

    if(optionalBook.isEmpty()) {
      return Optional.empty();
    }

    Optional<Author> optionalAuthor = authorRepository.findById(authorId);

    if(optionalAuthor.isEmpty()) {
      return Optional.empty();
    }

    Book book = optionalBook.get();
    Author author = optionalAuthor.get();

    book.getAuthors().add(author);
    Book newBook = bookRepository.save(book);

    return Optional.of(newBook);
  }

  @Transactional
  public Optional<Book> removeAuthor(Long bookId, Long authorId) {

    Optional<Book> optionalBook = bookRepository.findById(bookId);

    if(optionalBook.isEmpty()) {
      return Optional.empty();
    }

    Optional<Author> optionalAuthor = authorRepository.findById(authorId);

    if(optionalAuthor.isEmpty()) {
      return Optional.empty();
    }

    Book book = optionalBook.get();
    Author author = optionalAuthor.get();

    book.getAuthors().remove(author);
    Book newBook = bookRepository.save(book);

    return Optional.of(newBook);
  }
}