package com.example.BookStore.Controller;

import com.example.BookStore.Entity.Book;
import com.example.BookStore.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookController {

    @Autowired
    BookService bookService;

    @GetMapping("/")
    public String welcome(@RequestParam(value = "name", defaultValue = "Visitor") String name){
        return "<html><h1>Hi "+ name  +" Welcome to the Book Management System</h1></html>";
    }

    @GetMapping("/book")
    public List<Book> showAllBooks(){
        return this.bookService.showBooks();
    }

    @PostMapping("/book")
    public Book addBook(@RequestBody Book b){
        return this.bookService.addBook(b);
    }

    @GetMapping("/book/{bookId}")
    public Book showBookByID(@PathVariable Integer bookId){
        return this.bookService.showBook(bookId);
    }

    @DeleteMapping("/book/{bookId}")
    public String deleteBook(@PathVariable Integer bookId){
        return this.bookService.deleteBook(bookId);
    }

    @PutMapping("/book")
    public Book updateBook(@RequestBody Book book){
        return this.bookService.updateBook(book);
    }

}
