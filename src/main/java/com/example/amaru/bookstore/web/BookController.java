package com.example.amaru.bookstore.web;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.amaru.bookstore.model.BookRepository;

@Controller

public class BookController {
	
	@Autowired
	private BookRepository repository;
	
	@RequestMapping(value = {"/", "/booklist"})
	public String listBooks(Model model) {
		model.addAttribute("books", repository.findAll());
		return "booklist";
	}
	
}
