package com.example.librarysystem.repo;

import com.example.librarysystem.model.Books;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BooksRepo extends JpaRepository<Books, Long> {
    void deleteBooksById(Long id);
//    Page<Books> findAllBooks(PageRequest pageRequest);
//    @Procedure("FIND_BOOK_BY_NAME_AUTHOR")
//    Optional<List<Books>> find(String search);

    Optional<Books> findBooksById(Long id);
    @Query(value = "SELECT * FROM books WHERE `name` LIKE :search OR `author` LIKE :search",nativeQuery = true)
    Optional<List<Books>> findSearched(@Param("search") String search);

}
