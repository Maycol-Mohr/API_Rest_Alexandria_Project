package com.betrybe.alexandria2.controllers;

import com.betrybe.alexandria2.dtos.PublisherDTO;
import com.betrybe.alexandria2.dtos.ResponseDTO;
import com.betrybe.alexandria2.entities.Publisher;
import com.betrybe.alexandria2.services.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/publishers")
public class PublisherController {

  @Autowired
  private PublisherService publisherService;

  @PostMapping()
  public ResponseEntity<ResponseDTO<Publisher>> createPublisher(@RequestBody PublisherDTO publisherDTO) {
    Publisher newPublisher = publisherService.insertPublisher(publisherDTO.toPublisher());
    ResponseDTO<Publisher> responseDTO = new ResponseDTO<>("Editor criado com sucesso!", newPublisher);
    return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
  }

  @PutMapping("/{publisherId}")
  public ResponseEntity<ResponseDTO<Publisher>> updatePublisher(@PathVariable Long publisherId, @RequestBody PublisherDTO publisherDTO) {
    Optional<Publisher> optionalPublisher = publisherService.updatePublisher(publisherId, publisherDTO.toPublisher());

    if (optionalPublisher.isEmpty()) {
      ResponseDTO<Publisher> responseDTO = new ResponseDTO<>(
              String.format("Não foi encontrado o editor de ID %d", publisherId), null);
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDTO);
    }

    ResponseDTO<Publisher> responseDTO = new ResponseDTO<>(
            "Editor atualizado com sucesso!", optionalPublisher.get());
    return ResponseEntity.ok(responseDTO);
  }

  @DeleteMapping("/{publisherId}")
  public ResponseEntity<ResponseDTO<Publisher>> removePublisherById(@PathVariable Long publisherId) {
    Optional<Publisher> optionalPublisher = publisherService.removePublisherById(publisherId);

    if (optionalPublisher.isEmpty()) {
      ResponseDTO<Publisher> responseDTO = new ResponseDTO<>(
              String.format("Não foi encontrado o Editor de ID %d", publisherId), null);
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDTO);
    }

    ResponseDTO<Publisher> responseDTO = new ResponseDTO<>("Editor removido com sucesso!", null);
    return ResponseEntity.ok(responseDTO);
  }

  @GetMapping("/{publisherId}")
  public ResponseEntity<ResponseDTO<Publisher>> getPublisherById(@PathVariable Long publisherId) {
    Optional<Publisher> optionalPublisher = publisherService.getPublisherById(publisherId);

    if (optionalPublisher.isEmpty()) {
      ResponseDTO<Publisher> responseDTO = new ResponseDTO<>(
              String.format("Não foi encontrado o Editor de ID %d", publisherId), null);
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDTO);
    }

    ResponseDTO<Publisher> responseDTO = new ResponseDTO<>("Editor encontrado com sucesso!", optionalPublisher.get());
    return ResponseEntity.ok(responseDTO);
  }

  @GetMapping()
  public List<PublisherDTO> getAllPublishers() {
    List<Publisher> allPublishers = publisherService.getAllPublishers();
    return allPublishers.stream()
            .map((publisher) -> new PublisherDTO(publisher.getId(), publisher.getName(), publisher.getAddress(), publisher.getBooks()))
            .collect(Collectors.toList());
  }
}