package com.jaswanth.bookapi.controller;

import com.jaswanth.bookapi.model.Book;
import com.jaswanth.bookapi.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.jaswanth.bookapi.exception.ResourceNotFoundException;
import com.jaswanth.bookapi.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@CrossOrigin("*")
@Controller
@RequestMapping("/")
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookService bookService;


    @GetMapping("")
    public String showHomePage() {
        return "index";  // Redirect to the static file
    }

    @GetMapping("/books")
    public String showAllBooks(Model model) {
        List<Book> showBooks = bookService.listAll();
        model.addAttribute("showBooks", showBooks);
        return "books";
    }

    @GetMapping("/book/new")
    public String showNewForm(Model model) {
        model.addAttribute("book", new Book());
        model.addAttribute("pageTitle", "Add New Book");
        return "book_form";
    }

    @PostMapping("/book/save")
    public String saveBook(Book book, RedirectAttributes ra) {
        bookService.save(book);
        ra.addFlashAttribute("message", "The book has been saved successfully.");
        return "redirect:/books";
    }

    @PostMapping("/book/save/{id}")
    public String saveBooks(Book book, RedirectAttributes ra) {
        bookService.save(book);
        ra.addFlashAttribute("message", "The book has been saved successfully.");
        return "redirect:/books";
    }

    @GetMapping("/book/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model, RedirectAttributes ra) {
        try {
            Book book = bookService.get(id);
            model.addAttribute("book", book);
            model.addAttribute("pageTitle", "Edit Book (ID: " + id + ")");

            return "book_form";
        } catch (ResourceNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
            return "redirect:/books";
        }
    }

    @DeleteMapping("/book/delete/{id}")
    public String deleteBook(@PathVariable("id") Long id, RedirectAttributes ra) {
        try {
            bookService.delete(id);
            ra.addFlashAttribute("message", "The Book ID " + id + " has been deleted.");
        } catch (ResourceNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/books";
    }
//    @GetMapping
//    public List<Book> getAllBooks(){
//        return bookRepository.findAll();
//    }
//    // build create Book REST API
//    @PostMapping
//    public Book createBook(@RequestBody Book book) {
//        return bookRepository.save(book);
//    }
//
//    // build get Book by id REST API
//    @GetMapping("{id}")
//    public ResponseEntity<Book> getBookById(@PathVariable  long id){
//        Book book = bookRepository.findById(id)
//                .orElseThrow(() -> new ResourceNotFoundException("Book does not exist with id:" + id));
//        return ResponseEntity.ok(book);
//    }
//
//    // build update Book REST API
//    @PutMapping("{id}")
//    public ResponseEntity<Book> updateBook(@PathVariable long id,@RequestBody Book bookDetails) {
//        Book updateBook = bookRepository.findById(id)
//                .orElseThrow(() -> new ResourceNotFoundException("Book does not exist with id: " + id));
//
//        updateBook.setBookName(bookDetails.getBookName());
//        updateBook.setBookauthor(bookDetails.getBookauthor());
//        updateBook.setBookavailability(bookDetails.getBookavailability());
//
//        bookRepository.save(updateBook);
//
//        return ResponseEntity.ok(updateBook);
//    }
//
//    // build delete Book REST API
//    @DeleteMapping("{id}")
//    public ResponseEntity<HttpStatus> deleteBook(@PathVariable long id){
//
//        Book book = bookRepository.findById(id)
//                .orElseThrow(() -> new ResourceNotFoundException("Book does not exist with id: " + id));
//

//        bookRepository.delete(book);
//
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//
//    }
}