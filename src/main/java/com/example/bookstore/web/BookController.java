package com.example.bookstore.web;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.bookstore.domain.Book;

public class BookController {

	@RequestMapping(value="/index")
	public String showIndex(Model model) {
		model.addAttribute("book", new Book());
		return "form";
	}
}
