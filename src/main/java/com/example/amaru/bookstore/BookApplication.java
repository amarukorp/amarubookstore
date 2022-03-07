package com.example.amaru.bookstore;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.amaru.bookstore.model.Book;
import com.example.amaru.bookstore.model.BookRepository;
import com.example.amaru.bookstore.model.Category;
import com.example.amaru.bookstore.model.CategoryRepository;
import com.example.amaru.bookstore.model.User;
import com.example.amaru.bookstore.model.UserRepository;

@SpringBootApplication
public class BookApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner bookDemo(BookRepository repository, CategoryRepository crepository, UserRepository urepository) {
		return (args) -> {
			System.out.println("save a couple of categories");
			crepository.save(new Category("Economy"));
			crepository.save(new Category("Self-improvement"));
			crepository.save(new Category("Coding"));
			
			System.out.println("save a couple of students");
			repository.save(new Book("What money can't buy: the moral limits of markets", "Michael Sandel", 2012, "9781846144714", 15.00, crepository.findByName("Economy").get(0) ));
			repository.save(new Book("How to win friends and influence people", "Dale Carnegie", 1998, "0671027034", 15.00, crepository.findByName("Self-improvement").get(0) ));
			
			// Create users with BCrypt encoded password (user/user, admin/admin)
			//String username, String passwordHash, String email, String role
			
			User user1 = new User("user", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "user.user@email.com" ,"USER");
			User user2 = new User("admin", "$2a$08$bCCcGjB03eulCWt3CY0AZew2rVzXFyouUolL5dkL/pBgFkUH9O4J2", "admin.admin@email.com" ,"ADMIN");
			urepository.save(user1);
			urepository.save(user2);
		
			System.out.println("fetch all books");
			for (Book book : repository.findAll()) {
				System.out.println(book.toString());
			}
		};
		
	}

}
