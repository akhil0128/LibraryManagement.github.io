package com.example.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.book.dto.BookDTO;
import com.example.entity.Book;
import com.example.exception.BookAPIException;
import com.example.repository.BookRepository;

@SpringBootTest
public class BookServiceTest {

	@InjectMocks
	BookService bookservice;
	@Mock
	BookRepository bookrepo;
	
	@Test
	void testaddBookToDBWithBookAlreadyAvailable() {
		when(bookrepo.findById(ArgumentMatchers.anyInt())).thenReturn(Optional.of(new Book()));
		assertThrows(BookAPIException.class, ()-> bookservice.addBookToDB(new BookDTO()));
	}
	
	@Test
	void testaddBookToDBWithSuccess() {
		when(bookrepo.findById(ArgumentMatchers.anyInt())).thenReturn(Optional.empty());
		when(bookrepo.save(ArgumentMatchers.any())).thenReturn(new Book());
		Book addBookInDB = bookservice.addBookToDB(new BookDTO());
		assertNotNull(addBookInDB);
	}
	
	@Test
	void testaddBookWithSuccessWithEquals() {
		when(bookrepo.findById(ArgumentMatchers.anyInt())).thenReturn(Optional.empty());
		Book e = new Book();
		e.setBookId(1001);
		when(bookrepo.save(ArgumentMatchers.any())).thenReturn(e);
		BookDTO bookDTO = new BookDTO();
		bookDTO.setBookId(1001);
		Book addBookInDB= bookservice.addBookToDB(bookDTO);
		assertEquals(bookDTO.getBookId(), addBookInDB.getBookId());
	}
	
	@Test
	void testIssueBookinBookAPIBookNotInDbScenario() {
		when(bookrepo.findById(ArgumentMatchers.anyInt())).thenReturn(Optional.empty());
		assertThrows(BookAPIException.class, () -> bookservice.issueBookToId(11, ArgumentMatchers.anyInt()));
	}

	@Test
	void testIssueBookinBookAPIStudWithZeroBookStudentId() {
		assertThrows(BookAPIException.class, () -> bookservice.issueBookToId(0, 0));
	}
	
	@Test
	void testReturnBookinBookAPIStudNotInDbScenario() {
		when(bookrepo.findById(ArgumentMatchers.anyInt())).thenReturn(Optional.empty());
		assertThrows(BookAPIException.class, () -> bookservice.returnBook(11, ArgumentMatchers.anyInt()));
	}

	@Test
	void testReturnBookinStudentAPIStudWithZeroBookStudentId() {
		assertThrows(BookAPIException.class, () -> bookservice.returnBook(0, 0));
	}
	
	@Test
	void testDeleteBookinBookAPIWithBookNotInDBScenario() {
		when(bookrepo.findById(ArgumentMatchers.anyInt())).thenReturn(Optional.empty());
		assertThrows(BookAPIException.class, () -> bookservice.deleteBookById(123));
	}
}