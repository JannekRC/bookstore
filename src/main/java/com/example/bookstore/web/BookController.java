package com.example.bookstore.web;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.bookstore.domain.Book;

public class BookController {

	@GetMapping("/index")
	public String formAccess(Model model) {
		model.addAttribute("book", new Book());
		return "form";
	}
}
