package com.reactspringbootlibrary.springbootlibrary.dao;

import com.reactspringbootlibrary.springbootlibrary.entity.Book;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository <Book, Long>{
    
}
