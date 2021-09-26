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

@SpringBootApplication
public class BookstoreApplication {
	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(BookRepository repository, CategoryRepository crepository) {
		return (args) -> {
			log.info("save a couple of students");
			crepository.save(new Category("Programming"));
			crepository.save(new Category("Business"));
			crepository.save(new Category("UX"));
			
			repository.save(new Book("Fun Book", "Jimmy Jom", Year.of(1980), "978-3-16-148410-0", BigDecimal.valueOf(9.95), crepository.findByName("Programming").get(0)));
			repository.save(new Book("Boring Book", "Limmy Lom", Year.of(1981), "979-3-16-148410-0", BigDecimal.valueOf(10), crepository.findByName("Business").get(0)));
			
			log.info("fetch all books");
			for (Book book : repository.findAll()) {
				log.info(book.toString());
			}

		};
	}
}
