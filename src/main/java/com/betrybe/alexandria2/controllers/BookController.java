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

    ResponseDTO<Book> responseDTO = new ResponseDTO<>(String.format("Livro encontrado com sucesso!"),
            optionalBook.get());
    return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
  }

  @GetMapping()
  public List<BookDTO> getAllBooks() {
    List<Book> allBooks = bookService.getAllBooks();
    return allBooks.stream()
            .map((book) -> new BookDTO(book.getId(), book.getTitle(), book.getGenre()))
            .collect(Collectors.toList());
  }

  @PostMapping("/{bookId}/details")
  public ResponseEntity<ResponseDTO<BookDetail>> createBookDetail(@PathVariable Long bookId, @RequestBody BookDetail bookDetail) {
    Optional<BookDetail> newBookDetail = bookService.insertBookDetail(bookId, bookDetail);
    ResponseDTO<BookDetail> responseDTO = new ResponseDTO<>("Detalhes do livro criado com sucesso!", bookDetail);
    return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
  }

  @PutMapping("/{bookId}/details/{id}")
  public ResponseEntity<ResponseDTO<BookDetail>> updateBookDetail(@PathVariable Long id, @RequestBody BookDetailDTO bookDetailDTO) {
    Optional<BookDetail> optionalBookDetail = bookService.updateBookDetail(id, bookDetailDTO.toBookDetail());

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

  @PutMapping("/{bookId}/publisher/{publisherId}")
  public ResponseEntity<ResponseDTO<Book>> setPublisherFromBook(@PathVariable Long bookId, @PathVariable Long publisherId) {
    Optional<Book> optionalBook = bookService.setPublisher(bookId, publisherId);

    if(optionalBook.isEmpty()) {
      ResponseDTO<Book> responseDTO = new ResponseDTO<>(
              String.format("Não foi encontrado o livro de ID %d ou a editora de ID %d", bookId, publisherId), null);
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDTO);
    }

    ResponseDTO<Book> responseDTO = new ResponseDTO<>(
            "Editora vinculada ao livro com sucesso!", optionalBook.get());
    return ResponseEntity.ok(responseDTO);
  }

  @DeleteMapping("/{bookId}/publisher")
  public ResponseEntity<ResponseDTO<Book>> removePublisherFromBook(@PathVariable Long bookId) {
    Optional<Book> optionalBook = bookService.removePublisher(bookId);
    if(optionalBook.isEmpty()){
      ResponseDTO<Book> responseDTO = new ResponseDTO<>(
              String.format("Não foi possível remover a editora do livro com id %d", bookId),
              null
      );

      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDTO);
    }

    ResponseDTO<Book> responseDTO = new ResponseDTO<>(
            String.format("Editora removida do livro de ID %d", bookId),
            optionalBook.get()
    );

    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDTO);
  }

  @PutMapping("/{bookId}/author/{authorId}")
  public ResponseEntity<ResponseDTO<Book>> setAuthor(@PathVariable Long bookId, @PathVariable Long authorId) {
    Optional<Book> optionalBook = bookService.setAuthor(bookId, authorId);

    if(optionalBook.isEmpty()) {
      ResponseDTO<Book> responseDTO = new ResponseDTO<>(
              String.format("Não foi possível associar o livro de ID %d com a pessoa autora de ID %d", bookId, authorId), null);
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDTO);
    }

    ResponseDTO<Book> responseDTO = new ResponseDTO<>(
            "Pessoa autora associada com sucesso!", optionalBook.get());
    return ResponseEntity.ok(responseDTO);
  }

  @DeleteMapping("/{bookId}/author/{authorId}")
  public ResponseEntity<ResponseDTO<Book>> removeAuthor(@PathVariable Long bookId, @PathVariable Long authorId) {
    Optional<Book> optionalBook = bookService.removeAuthor(bookId, authorId);
    if(optionalBook.isEmpty()){
      ResponseDTO<Book> responseDTO = new ResponseDTO<>(
              String.format("Não foi possível remover a editora do livro com id %d", bookId),
              null
      );

      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDTO);
    }

    ResponseDTO<Book> responseDTO = new ResponseDTO<>(
            String.format("Pessoa autora desassociada com sucesso!"),
            optionalBook.get()
    );

    return ResponseEntity.ok(responseDTO);
  }
}