package com.betrybe.alexandria2.controllers;

import com.betrybe.alexandria2.dtos.AuthorDTO;
import com.betrybe.alexandria2.dtos.ResponseDTO;
import com.betrybe.alexandria2.entities.Author;
import com.betrybe.alexandria2.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/authors")
public class AuthorController {

  @Autowired
  private AuthorService authorService;

  @PostMapping()
  public ResponseEntity<ResponseDTO<Author>> createAuthor(@RequestBody AuthorDTO authorDTO) {
    Author newAuthor = authorService.insertAuthor(authorDTO.toAuthor());
    ResponseDTO<Author> responseDTO = new ResponseDTO<>("Autor criado com sucesso!", newAuthor);
    return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
  }

  @PutMapping("/{authorId}")
  public ResponseEntity<ResponseDTO<Author>> updateAuthor(@PathVariable Long authorId, @RequestBody AuthorDTO authorDTO) {
    Optional<Author> optionalAuthor = authorService.updateAuthor(authorId, authorDTO.toAuthor());

    if (optionalAuthor.isEmpty()) {
      ResponseDTO<Author> responseDTO = new ResponseDTO<>(
              String.format("Não foi encontrado o autor de ID %d", authorId), null);
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDTO);
    }

    ResponseDTO<Author> responseDTO = new ResponseDTO<>(
            "Autor atualizado com sucesso!", optionalAuthor.get());
    return ResponseEntity.ok(responseDTO);
  }

  @DeleteMapping("/{authorId}")
  public ResponseEntity<ResponseDTO<Author>> removeAuthorById(@PathVariable Long authorId) {
    Optional<Author> optionalAuthor = authorService.removeAuthorById(authorId);

    if (optionalAuthor.isEmpty()) {
      ResponseDTO<Author> responseDTO = new ResponseDTO<>(
              String.format("Não foi encontrado o autor de ID %d", authorId), null);
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDTO);
    }

    ResponseDTO<Author> responseDTO = new ResponseDTO<>("Autor removido com sucesso!", null);
    return ResponseEntity.ok(responseDTO);
  }

  @GetMapping("/{authorId}")
  public ResponseEntity<ResponseDTO<Author>> getAuthorById(@PathVariable Long authorId) {
    Optional<Author> optionalAuthor = authorService.getAuthorById(authorId);

    if (optionalAuthor.isEmpty()) {
      ResponseDTO<Author> responseDTO = new ResponseDTO<>(
              String.format("Não foi encontrado o autor de ID %d", authorId), null);
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDTO);
    }

    ResponseDTO<Author> responseDTO = new ResponseDTO<>("Autor encontrado com sucesso!", optionalAuthor.get());
    return ResponseEntity.ok(responseDTO);
  }

  @GetMapping()
  public List<AuthorDTO> getAllAuthors() {
    List<Author> allAuthors = authorService.getAllAuthors();
    return allAuthors.stream()
            .map((author) -> new AuthorDTO(author.getId(), author.getName(), author.getNationality()))
            .collect(Collectors.toList());
  }
}