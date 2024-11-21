package com.jaswanth.bookapi.service;

import com.jaswanth.bookapi.exception.ResourceNotFoundException;
import com.jaswanth.bookapi.model.Book;
import com.jaswanth.bookapi.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    @Autowired private BookRepository repo;

    public List<Book> listAll() {
        return (List<Book>) repo.findAll();
    }

    public void save(Book book) {
        repo.save(book);
    }

    public Book get(long bookId) throws ResourceNotFoundException {
        Optional<Book> result = repo.findById(bookId);
        if (result.isPresent()) {
            return result.get();
        }
        throw new ResourceNotFoundException("Could not find any Books with ID " + bookId);
    }

    public void delete(long bookId) throws ResourceNotFoundException {
        Long count = repo.countByBookId(bookId);
        if (count == null || count == 0) {
            throw new ResourceNotFoundException("Could not find any Books with ID " + bookId);
        }
        repo.deleteById(bookId);
    }
}