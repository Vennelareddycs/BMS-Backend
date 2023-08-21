package com.bms.controller;

import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.bms.exception.ResourceNotFoundException;
import com.bms.model.Book;
import com.bms.repository.BookRepository;

@CrossOrigin("*")
@RestController 
@RequestMapping("/Book")
public class BookController {
	@Autowired
	private BookRepository bookRepo;
	
	@GetMapping
	public List<Book> getAllBook(){
		return bookRepo.findAll();
	}
	
	@PostMapping
	public Book createBook(@RequestBody Book book) {
		return bookRepo.save(book);
	}
	  
	@GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable(value="id") long id)throws ResourceNotFoundException {
		 Book book = bookRepo.findBookById(id)
                .orElseThrow(()->new ResourceNotFoundException("book not found:: "+id));
        return ResponseEntity.ok().body(book);
    }

	@GetMapping("/featured")
	public ResponseEntity<List<Book>> getFeaturedBooks(){
		List<Book>featuredBooks=bookRepo.findByFeaturedTrue();
		return ResponseEntity.ok(featuredBooks);
	}
	@GetMapping("/search")
	public ResponseEntity<List<Book>> searchBooks(@RequestParam(value = "query", required = false) String query) {
	    List<Book> searchResults;

	    if (query != null && !query.trim().isEmpty()) {
	        searchResults = bookRepo.findByTitleContainingIgnoreCaseOrAuthorContainingIgnoreCase(query, query);
	    } else {
	        // No search criteria provided
	        return ResponseEntity.badRequest().build();
	    }

	    if (searchResults.isEmpty()) {
	        return ResponseEntity.notFound().build();
	    }

	    return ResponseEntity.ok(searchResults);
	}



	
	/*
	 * @GetMapping("/search") public ResponseEntity<List<Book>>
	 * searchBooks(@RequestParam(value = "title", required = false) String title,
	 * 
	 * @RequestParam(value = "author", required = false) String author){ List<Book>
	 * searchResults; if (title != null && author != null) { searchResults =
	 * bookRepo.findByTitleContainingIgnoreCaseAndAuthorContainingIgnoreCase(title,
	 * author); } else if (title != null) { searchResults =
	 * bookRepo.findByTitleContainingIgnoreCase(title); } else if (author != null) {
	 * searchResults = bookRepo.findByAuthorContainingIgnoreCase(author); } else {
	 * // No search criteria provided return ResponseEntity.badRequest().build(); }
	 * 
	 * if (searchResults.isEmpty()) { return ResponseEntity.notFound().build(); }
	 * 
	 * return ResponseEntity.ok(searchResults); }
	 */
	

	@PutMapping("/update/{id}")
	public ResponseEntity<Book> updateBook(@PathVariable(value = "id") long id,
	        @Validated @RequestBody Book bookDetails)
	        throws ResourceNotFoundException {
	    Book book = bookRepo.findBookById(id)
	            .orElseThrow(() -> new ResourceNotFoundException("Book not found for this id :: " + id));

	    book.setTitle(bookDetails.getTitle());
	    book.setAuthor(bookDetails.getAuthor());
	    book.setImageUrl(bookDetails.getImageUrl());
	    book.setPrice(bookDetails.getPrice());
	    book.setFeatured(bookDetails.isFeatured()); 
	    Book updatedBook = bookRepo.save(book);
	    return ResponseEntity.ok(updatedBook);
	}
	
	  @DeleteMapping("/delete/{id}")
	    public ResponseEntity deleteBook(@PathVariable(value = "id") int  id)throws ResourceNotFoundException {
	       Book book=bookRepo.findBookById(id)
	                .orElseThrow(() -> new ResourceNotFoundException("Book not found for this id :: " +id ));
	        this.bookRepo.delete(book);
	        return ResponseEntity.ok("Book with ID: " + id + " has been deleted successfully.");
	    }
		
}
