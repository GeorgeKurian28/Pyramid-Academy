package com.example.BookStore.Service;

import com.example.BookStore.Entity.Book;

import java.util.List;

public interface BookService {
    Book addBook(Book b);
    List<Book> showBooks();
    Book showBook(Integer bookId);
    Book updateBook(Book b);
    String deleteBook(Integer bookId);
}
