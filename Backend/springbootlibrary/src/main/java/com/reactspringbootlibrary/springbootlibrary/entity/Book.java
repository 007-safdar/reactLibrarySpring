package com.reactspringbootlibrary.springbootlibrary.entity;
import lombok.Data;
//Getters and Setters
import javax.persistence.*;
//create everything we need to create these tables as entity

@Entity
@Table(name="book")
@Data

public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private long id;
    @Column(name="title")
    private String title;
    @Column(name="author")
    private String author;
    @Column(name="description")
    private String description;
    @Column(name="copies")
    private int copies;
    @Column(name="copies_available")
    private int copiesAvailable;
    @Column(name="category")
    private String category;
    @Column(name="img")
    private String img;

}
