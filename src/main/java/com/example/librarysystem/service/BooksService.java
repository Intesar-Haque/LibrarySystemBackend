package com.example.librarysystem.service;

import com.example.librarysystem.model.Books;
import com.example.librarysystem.repo.BooksRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.librarysystem.exception.BookNotFoundException;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class BooksService {
    private final BooksRepo booksRepo;

    @Autowired
    public BooksService(BooksRepo booksRepo) {
        this.booksRepo = booksRepo;
    }

    public Books addBook(Books books) {
        return booksRepo.save(books);
    }

    public List<Books> findALlBooks() {
        return booksRepo.findAll();
    }

    public Books updateBook(Books books) {
        return booksRepo.save(books);
    }

    public Books findBookById(Long id) {
        return booksRepo.findBooksById(id)
                .orElseThrow(() -> new BookNotFoundException("Book by id " + id + " was not found"));
    }

    public void deleteBook(Long id){
        booksRepo.deleteBooksById(id);
    }
}
