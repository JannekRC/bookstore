package com.example.bookstore;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.assertj.core.api.Assertions.*;

import java.math.BigDecimal;
import java.time.Year;
import java.util.List;

import com.example.bookstore.domain.Book;
import com.example.bookstore.domain.BookRepository;
import com.example.bookstore.domain.Category;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class BookRepositoryTest {
   @Autowired
   private BookRepository repository;
   
   @Test
    public void findByLastnameShouldReturnBook() {
        List<Book> books = repository.findByAuthor("Jimmy Jom");
        assertThat(books).hasSize(1);
        assertThat(books.get(0).getTitle()).isEqualTo("Fun Book");
    }
   
   
   @Test
   public void createNewBook() {
     Book book = new Book("Mouse", "Mickey", Year.of(1969), "979-2-16-148410-0", BigDecimal.valueOf(49.99), new Category("BITE"));
     repository.save(book);
     assertThat(book.getId()).isNotNull();
   }
   
   @Test
   public void deleteBook() {
	   List<Book> books = repository.findByAuthor("Jimmy Jom");
	   repository.deleteById(books.get(0).getId());
	   assertThat(books.get(0).getId()).isNull();
   }
}
