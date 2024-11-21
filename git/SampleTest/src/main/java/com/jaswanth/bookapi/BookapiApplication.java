package com.jaswanth.bookapi;

import com.jaswanth.bookapi.model.Book;
import com.jaswanth.bookapi.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BookapiApplication implements CommandLineRunner {

	public static void main(String[] args) {

		SpringApplication.run(BookapiApplication.class, args);
	}

	@Autowired
	private BookRepository bookRepository;

	@Override
	public void run(String... args) throws Exception {
//		Book book = new Book();
//		book.setBookauthor("Ramesh");
//		book.setBookName("Titanic");
//		book.setBookavailability("30");
//		bookRepository.save(book);
//
//		Book book1 = new Book();
//		book1.setBookauthor("John");
//		book1.setBookName("Freedom");
//		book1.setBookavailability("100");
//		bookRepository.save(book1);
	}
}
