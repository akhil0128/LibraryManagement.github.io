package com.example.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.book.dto.BookDTO;
import com.example.entity.Book;
import com.example.service.BookService;

@RestController
@CrossOrigin
public class BookController {
	@Autowired
	BookService bookservice;

	@PostMapping("/book")
	public ResponseEntity<Book> addBook(@Valid @RequestBody BookDTO bookDTO) {
		Book addedBook = bookservice.addBookToDB(bookDTO);
		return new ResponseEntity<Book>(addedBook, HttpStatus.CREATED);
	}

	@GetMapping("/book/getbook/{bookId}")
	public Book getBookById(@PathVariable int bookId) {
		return bookservice.getBookById(bookId);
	}

	@PutMapping("/book/issuebook")
	public Book issueBook(int bookId, int studentId) {
		if (bookId == 14) {
			throw new RuntimeException();
		}
		return bookservice.issueBookToId(bookId, studentId);
	}

	@PutMapping("/book/returnbook")
	public Book returnBook(int bookId, int studentId) {
		return bookservice.returnBook(bookId, studentId);
	}

	@DeleteMapping("/book/deletebook/{bookId}")
	public Book deleteBookById(@PathVariable int bookId) {
		return bookservice.deleteBookById(bookId);
	}
}