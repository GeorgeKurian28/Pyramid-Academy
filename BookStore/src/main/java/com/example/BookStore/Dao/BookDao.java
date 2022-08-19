package com.example.BookStore.Dao;

import com.example.BookStore.Entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookDao extends JpaRepository<Book, Integer> {
}
