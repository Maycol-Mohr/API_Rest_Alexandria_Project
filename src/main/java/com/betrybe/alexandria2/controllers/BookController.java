package com.betrybe.alexandria2.controllers;

import com.betrybe.alexandria2.dtos.BookDTO;
import com.betrybe.alexandria2.dtos.BookDetailDTO;
import com.betrybe.alexandria2.dtos.ResponseDTO;
import com.betrybe.alexandria2.estities.Book;
import com.betrybe.alexandria2.estities.BookDetail;
import com.betrybe.alexandria2.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/books")
public class BookController {

  @Autowired
  private BookService bookService;

  @PostMapping()
  public ResponseEntity<ResponseDTO<Book>> createBook(@RequestBody BookDTO bookDTO) {
    Book newBook = bookService.insertBook(bookDTO.toBook());
    ResponseDTO<Book> responseDTO = new ResponseDTO<>("Livro criado com sucesso!", newBook);
    return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
  }

  @PutMapping("/{bookId}")
  public ResponseEntity<ResponseDTO<Book>> updateBook(@PathVariable Long bookId, @RequestBody BookDTO bookDTO) {
    Optional<Book> optionalBook = bookService.updateBook(bookId, bookDTO.toBook());

    if (optionalBook.isEmpty()) {
      ResponseDTO<Book> responseDTO = new ResponseDTO<>(
              String.format("Não foi encontrado o livro de ID %d", bookId), null);
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDTO);
    }

    ResponseDTO<Book> responseDTO = new ResponseDTO<>(
            "Livro atualizado com sucesso!", optionalBook.get());
    return ResponseEntity.ok(responseDTO);
  }

  @DeleteMapping("/{bookId}")
  public ResponseEntity<ResponseDTO<Book>> removeBookById(@PathVariable Long bookId) {
    Optional<Book> optionalBook = bookService.removeBookById(bookId);

    if (optionalBook.isEmpty()) {
      ResponseDTO<Book> responseDTO = new ResponseDTO<>(
              String.format("Não foi encontrado o livro de ID %d", bookId), null);
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDTO);
    }

    ResponseDTO<Book> responseDTO = new ResponseDTO<>("Livro removido com sucesso!", null);
    return ResponseEntity.ok(responseDTO);
  }

  @GetMapping("/{bookId}")
  public ResponseEntity<ResponseDTO<Book>> getBookById(@PathVariable Long bookId) {
    Optional<Book> optionalBook = bookService.getBookById(bookId);

    if (optionalBook.isEmpty()) {
      ResponseDTO<Book> responseDTO = new ResponseDTO<>(
              String.format("Não foi encontrado o livro de ID %d", bookId), null);
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDTO);
    }

    ResponseDTO<Book> responseDTO = new ResponseDTO<>("Livro encontrado com sucesso!", optionalBook.get());
    return ResponseEntity.ok(responseDTO);
  }

  @GetMapping()
  public List<BookDTO> getAllBooks() {
    List<Book> allBooks = bookService.getAllBooks();
    return allBooks.stream()
            .map((book) -> new BookDTO(book.getId(), book.getTitle(), book.getGenre()))
            .collect(Collectors.toList());
  }

  @PostMapping("/{bookId}/details")
  public ResponseEntity<ResponseDTO<BookDetail>> createBookDetail(@RequestBody BookDetailDTO bookDetailDTO) {
    BookDetail newBookDetail = bookService.insertBookDetails(bookDetailDTO.toBookDetail());
    ResponseDTO<BookDetail> responseDTO = new ResponseDTO<>("Detalhes do livro criado com sucesso!", newBookDetail);
    return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
  }

  @PutMapping("/{bookId}/details/{id}")
  public ResponseEntity<ResponseDTO<BookDetail>> updateBookDetail(@PathVariable Long id, @RequestBody BookDetailDTO bookDetailDTO) {
    Optional<BookDetail> optionalBookDetail = bookService.updateBookDetails(id, bookDetailDTO.toBookDetail());

    if (optionalBookDetail.isEmpty()) {
      ResponseDTO<BookDetail> responseDTO = new ResponseDTO<>(
              String.format("Não foi encontrado o livro detalhado de ID %d", id), null);
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDTO);
    }

    ResponseDTO<BookDetail> responseDTO = new ResponseDTO<>(
            "Livro detalhado atualizado com sucesso!", optionalBookDetail.get());
    return ResponseEntity.ok(responseDTO);
  }

  @DeleteMapping("/{bookId}/details/{id}")
  public ResponseEntity<ResponseDTO<BookDetail>> removeBookDetailById(@PathVariable Long id) {
    Optional<BookDetail> optionalBookDetail = bookService.removeBookDetailById(id);

    if (optionalBookDetail.isEmpty()) {
      ResponseDTO<BookDetail> responseDTO = new ResponseDTO<>(
              String.format("Não foi encontrado o livro detalhado de ID %d", id), null);
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDTO);
    }

    ResponseDTO<BookDetail> responseDTO = new ResponseDTO<>("Livro detalhado removido com sucesso!", null);
    return ResponseEntity.ok(responseDTO);
  }

  @GetMapping("/{bookId}/details/{id}")
  public ResponseEntity<ResponseDTO<BookDetail>> getBookDetailById(@PathVariable Long id) {
    Optional<BookDetail> optionalBookDetail = bookService.getBookDetailById(id);

    if (optionalBookDetail.isEmpty()) {
      ResponseDTO<BookDetail> responseDTO = new ResponseDTO<>(
              String.format("Não foi encontrado o livro detalhado de ID %d", id), null);
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDTO);
    }

    ResponseDTO<BookDetail> responseDTO = new ResponseDTO<>("Livro detalhado encontrado com sucesso!", optionalBookDetail.get());
    return ResponseEntity.ok(responseDTO);
  }
}