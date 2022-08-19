package com.example.BookStore.Entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "tbl_Books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "c_Id")
    private int bookId;
    private String bookName;
    private int quantity;

    public Book(String bookName, int quantity) {
        this.bookName = bookName;
        this.quantity = quantity;
    }

    public Book() {
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
