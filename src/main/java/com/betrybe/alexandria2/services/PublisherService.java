package com.betrybe.alexandria2.services;

import com.betrybe.alexandria2.entities.Publisher;
import com.betrybe.alexandria2.repositories.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PublisherService {

  @Autowired
  private PublisherRepository publisherRepository;

  @Transactional
  public Publisher insertPublisher(Publisher publisher) {

    return publisherRepository.save(publisher);
  }

  @Transactional
  public Optional<Publisher> updatePublisher(Long id, Publisher publisher) {

    Optional<Publisher> optionalPublisher = publisherRepository.findById(id);

    if(optionalPublisher.isPresent()) {
      Publisher publisherFromDB = optionalPublisher.get();
      publisherFromDB.setName(publisher.getName());
      publisherFromDB.setAddress(publisher.getAddress());

      Publisher updatedPublisher = publisherRepository.save(publisherFromDB);
      return Optional.of(updatedPublisher);

    }

    return optionalPublisher;
  }

  @Transactional
  public Optional<Publisher> removePublisherById(Long id) {

    Optional<Publisher> publisherOptional = publisherRepository.findById(id);

    if(publisherOptional.isPresent()) {
      publisherRepository.deleteById(id);
    }

    return publisherOptional;
  }

  @Transactional(readOnly = true)
  public Optional<Publisher> getPublisherById(Long id) {
    return publisherRepository.findById(id);
  }

  @Transactional(readOnly = true)
  public List<Publisher> getAllPublishers() {

    return publisherRepository.findAll();
  }
}