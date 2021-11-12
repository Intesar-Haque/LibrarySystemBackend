package com.example.librarysystem.controller;

import com.example.librarysystem.model.Books;
import com.example.librarysystem.repo.BooksRepo;
import com.example.librarysystem.service.BooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/book")
@CrossOrigin("http://localhost:4200")
public class BooksController {
    private final BooksService booksService;
    @Autowired
    BooksRepo booksRepo;

    public BooksController(BooksService booksService) {
        this.booksService = booksService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Books>> getAllBooks () {
        List<Books> books = booksService.findALlBooks();
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Books> getBookById (@PathVariable("id") Long id) {
        Books books = booksService.findBookById(id);
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Books> addBook(@RequestBody Books books) {
        Books newBooks = booksService.addBook(books);
        return new ResponseEntity<>(newBooks, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Books> updateBook(@RequestBody Books books) {
        Books updateBooks = booksService.updateBook(books);
        return new ResponseEntity<>(updateBooks, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable("id") Long id) {
        booksService.deleteBook(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/pages")
    public Page<Books> list(@RequestParam(name = "page", defaultValue = "0") int page,
                            @RequestParam(name = "size", defaultValue = "10") int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Books> pageResult = booksRepo.findAll(pageRequest);
        List<Books> books = new ArrayList<>();
        for(Books book : pageResult){
            books.add(book);
        }
        return new PageImpl<>(books, pageRequest, pageResult.getTotalElements());

    }
    @GetMapping("/search")
    public ResponseEntity<Optional<List<Books>>> findBook (@RequestParam String key) {
        key='%'+key+'%';
        Optional<List<Books>> books = booksRepo.findSearched(key);
        return new ResponseEntity<>(books, HttpStatus.OK);
    }
}
