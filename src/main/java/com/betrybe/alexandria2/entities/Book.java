package com.betrybe.alexandria2.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "books")
public class Book {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String title;

  private String genre;

  @OneToOne(cascade = CascadeType.ALL, mappedBy = "book")
  private BookDetail details;

  @ManyToOne
  @JoinColumn(name = "publisher_id")
  private Publisher publisher;

  @ManyToMany
  @JoinTable(
          name = "author_books",
          joinColumns = @JoinColumn(name = "book_id"),
          inverseJoinColumns = @JoinColumn(name = "author_id")
  )
  private List<Author> authors;

  public Book() {}

  public Book(Long id, String title, String genre, BookDetail details, Publisher publisher, List<Author> authors) {
    this.id = id;
    this.title = title;
    this.genre = genre;
    this.details = details;
    this.publisher = publisher;
    this.authors = authors;
  }

  public Book(Long id, String title, String genre) {
    this.id = id;
    this.title = title;
    this.genre = genre;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getGenre() {
    return genre;
  }

  public void setGenre(String genre) {
    this.genre = genre;
  }

  public BookDetail getDetails() {
    return details;
  }

  public void setDetails(BookDetail details) {
    this.details = details;
  }

  public Publisher getPublisher() {
    return publisher;
  }

  public void setPublisher(Publisher publisher) {
    this.publisher = publisher;
  }

  public List<Author> getAuthors() {
    return authors;
  }

  public void setAuthors(List<Author> authors) {
    this.authors = authors;
  }
}