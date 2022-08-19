package com.example.BookStore.Service;

import com.example.BookStore.Dao.BookDao;
import com.example.BookStore.Entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService{

    @Autowired
    BookDao bookDao;

    @Override
    public Book addBook(Book b) {
        return this.bookDao.save(b);
    }

    @Override
    public List<Book> showBooks() {
        return this.bookDao.findAll();
    }

    @Override
    public Book showBook(Integer bookId) {
        Book book = null;
        Optional<Book> opt = this.bookDao.findById(bookId);
        if(opt.isPresent())
            book = opt.get();
        else{
            throw new RuntimeException("This book Id does not exist");
        }
        return book;
    }

    @Override
    public Book updateBook(Book b) {
        return this.bookDao.save(b);
    }

    @Override
    public String deleteBook(Integer bookId) {
        this.bookDao.deleteById(bookId);
        return "Succesfully deleted";
    }


}
