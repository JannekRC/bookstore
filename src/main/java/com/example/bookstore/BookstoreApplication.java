package com.example.bookstore;

import java.math.BigDecimal;
import java.time.Year;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.bookstore.domain.Book;
import com.example.bookstore.domain.BookRepository;
import com.example.bookstore.domain.Category;
import com.example.bookstore.domain.CategoryRepository;
import com.example.bookstore.domain.User;
import com.example.bookstore.domain.UserRepository;

@SpringBootApplication
public class BookstoreApplication {
	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(BookRepository repository, CategoryRepository crepository, UserRepository urepository) {
		return (args) -> {
			log.info("save a couple of books");
			
			// initiate categories
			crepository.save(new Category("Programming"));
			crepository.save(new Category("Business"));
			crepository.save(new Category("UX"));
			
			// demo books
			repository.save(new Book("Fun Book", "Jimmy Jom", Year.of(1980), "978-3-16-148410-0", BigDecimal.valueOf(9.95), crepository.findByName("Programming").get(0)));
			repository.save(new Book("Boring Book", "Limmy Lom", Year.of(1981), "979-3-16-148410-0", BigDecimal.valueOf(10), crepository.findByName("Business").get(0)));
			
			// demo users
			User user1 = new User("user", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "USER");
			User user2 = new User("admin", "$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C", "ADMIN");
			User user3 = new User("Jannek", "$2a$10$vbxEHK1ViYDjo7U1BEwgcOToL7Gk6kRFXE1CfNrnF.17C9cPvZb2y", "ADMIN");
			urepository.save(user1);
			urepository.save(user2);
			urepository.save(user3);
			
			log.info("fetch all books");
			for (Book book : repository.findAll()) {
				log.info(book.toString());
			}
			
			log.info("fetch all users");
			for (User user : urepository.findAll()) {
				log.info(user.toString());
			}

		};
	}
}
