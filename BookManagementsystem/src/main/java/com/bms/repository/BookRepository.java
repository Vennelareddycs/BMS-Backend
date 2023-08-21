package com.bms.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bms.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>{

	List<Book> findAll();
	Optional<Book> findBookById(long id);
	List<Book> findByFeaturedTrue();
//	List<Book> findByTitleContainingIgnoreCaseAndAuthorContainingIgnoreCase(String title, String author);
//	List<Book> findByTitleContainingIgnoreCase(String title);
//	List<Book> findByAuthorContainingIgnoreCase(String author);
	List<Book> findByTitleContainingIgnoreCaseOrAuthorContainingIgnoreCase(String query, String query2);
	

}
