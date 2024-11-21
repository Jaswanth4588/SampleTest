package com.jaswanth.bookapi.repository;

import com.jaswanth.bookapi.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface BookRepository extends JpaRepository<Book, Long> {
    public Long countByBookId(Long bookId);
}
