package com.example.amaru.bookstore;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.amaru.bookstore.model.Book;
import com.example.amaru.bookstore.model.BookRepository;

@SpringBootApplication
public class BookApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner bookDemo(BookRepository repository) {
		return (args) -> {
			System.out.println("save a couple of students");
			repository.save(new Book("What money can't buy: the moral limits of markets", "Michael Sandel", 2012, "9781846144714", 15.00));
			repository.save(new Book("How to win friends and influence people", "Dale Carnegie", 1998, "0671027034", 15.00));
		
			System.out.println("fetch all books");
			for (Book book : repository.findAll()) {
				System.out.println(book.toString());
			}
		};
		
	}

}
