package com.example.amaru.bookstore.web;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.amaru.bookstore.model.Book;
import com.example.amaru.bookstore.model.BookRepository;
import com.example.amaru.bookstore.model.CategoryRepository;

@Controller
public class BookController {
	
	@Autowired
	private BookRepository repository;
	
	@Autowired
	private CategoryRepository crepository;
	
	@RequestMapping(value = {"/", "/booklist"})
	public String listBooks(Model model) {
		model.addAttribute("books", repository.findAll());
		return "booklist";
	}
	
	@RequestMapping(value="/login")
	public String login() {
		return "login";
	}  
	
	//RESTful service to get all books
	@RequestMapping(value = "/books", method= RequestMethod.GET)
	public @ResponseBody List<Book> bookListRest(){
		return (List<Book>) repository.findAll();
	}
	
	//RESTful service that get books by id
	@RequestMapping(value= "/books/{id}", method = RequestMethod.GET)
	public @ResponseBody Optional<Book> findBookRest(@PathVariable("id") Long id){
		return repository.findById(id);
	}
	
    @RequestMapping(value = "/add")
    public String addBook(Model model){
    	model.addAttribute("book", new Book());
    	model.addAttribute("categories", crepository.findAll());
        return "addbook";
    } 
    
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(Book book){
        repository.save(book);
        return "redirect:booklist";
    } 
    
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteBook(@PathVariable("id") Long bookId, Model model) {
    	repository.deleteById(bookId);
        return "redirect:../booklist";
    } 
    @RequestMapping(value = "/edit/{id}", method= RequestMethod.GET)
    public String editBook (@PathVariable("id") Long id, Model model) {
    	model.addAttribute("book", repository.findById(id));
    	model.addAttribute("categories", crepository.findAll());
    	return "editbook";
    }
    
	
}
