package com.example.service;

import java.time.LocalDate;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.book.dto.BookDTO;
import com.example.entity.Book;
import com.example.exception.BookAPIException;
import com.example.repository.BookRepository;

@Service
public class BookService {

	@Autowired
	BookRepository bookrepo;

	public Book addBookToDB(BookDTO bookDTO) {
		Book book = bookDTO.convertTOEntity();
		Optional<Book> findById = bookrepo.findById(book.getBookId());
		if (findById.isPresent()) {
			throw new BookAPIException("Book with this Book id is already available");
		}
		return bookrepo.save(book);
	}

	public Book getBookById(int bookId) {
		Optional<Book> optionalBook = bookrepo.findById(bookId);
		Book bookDetailsFromDB = optionalBook.orElseThrow(() -> new BookAPIException("Book details do not exist"));
		return bookDetailsFromDB;
	}

	@Transactional
	public Book issueBookToId(int bookId, int studentId) {
		Optional<Book> optionalBook = bookrepo.findById(bookId);
		Book book = optionalBook.orElseThrow(() -> new BookAPIException("Book is not available in database"));
		if (book.getAvailability().equals("YES")) {
			book.setIssueedTo(studentId);
			book.setAvailability("No");
			book.setIssueDate(LocalDate.now());
			return bookrepo.save(book);
		} else {
			throw new BookAPIException("Book is issued to another student");
		}
	}

	@Transactional
	public Book returnBook(int bookId, int studentId) {
		Optional<Book> optionalBook = bookrepo.findById(bookId);
		Book book = optionalBook.orElseThrow(() -> new BookAPIException("Book does not exist"));
		if (book.getAvailability().equals("No")) {
			book.setIssueedTo(0);
			book.setAvailability("YES");
			book.setIssueDate(null);
			return bookrepo.save(book);
		} else {
			throw new BookAPIException("Book is issued to another student");
		}
	}

	public Book deleteBookById(int bookId) {
		Optional<Book> optionalBook = bookrepo.findById(bookId);
		Book book = optionalBook.orElseThrow(() -> new BookAPIException("Book does not exist"));
		bookrepo.deleteById(bookId);
		return book;
	}
}
