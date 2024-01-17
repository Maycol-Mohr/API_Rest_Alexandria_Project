package com.betrybe.alexandria2.estities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "publishers")
public class Publisher {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  public Long id;

  public String name;

  public String address;

  @OneToMany(mappedBy = "publisher")
  @JsonIgnore
  private List<Book> books;

  public Publisher() {
  }

  public Publisher(Long id, String name, String address, List<Book> books) {
    this.id = id;
    this.name = name;
    this.address = address;
    this.books = books;
  }

  public Publisher(Long id, String name, String address) {
    this.id = id;
    this.name = name;
    this.address = address;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public List<Book> getBooks() {
    return books;
  }

  public void setBooks(List<Book> books) {
    this.books = books;
  }
}