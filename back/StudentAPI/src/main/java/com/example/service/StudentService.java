package com.example.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.entity.Student;
import com.example.exception.StudentAPIException;
import com.example.repository.StudentRepository;
import com.example.student.dto.BookDTO;
import com.example.student.dto.StudentDTO;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Service
public class StudentService {

	@Autowired
	StudentRepository studrepo;

	@Autowired
	RestTemplate restTemplate;

	@Value("${url}")
	String url;

	// postforstudent
	public Student addStudentToDB(StudentDTO studentDTO) {
		Student student = studentDTO.convertToEntity();
		Optional<Student> findById = studrepo.findById(student.getStudentId());
		if (findById.isPresent()) {
			throw new StudentAPIException("Student with this student id is already available");
		}
		return studrepo.save(student);
	}

	// getforStudent
	public Student getStudentById(int studentId) {
		Optional<Student> optionalStudent = studrepo.findById(studentId);
		Student studDetailsFromDB = optionalStudent
				.orElseThrow(() -> new StudentAPIException("Student details do not exist"));
		return studDetailsFromDB;
	}

	// issuebook
	@CircuitBreaker(name = "StudentAPI-service", fallbackMethod = "issuebookfallback")
	@Transactional
	public StudentDTO issueBookToStudent(int bookId, int studentId) {
		if (bookId == 0 && studentId == 0) {
			throw new StudentAPIException("Either book id or student id is null");
		}
		Optional<Student> optionalStudent = studrepo.findById(studentId);
		Student student = optionalStudent.orElseThrow(() -> new StudentAPIException("Student does not exist"));
		BookDTO bookDTO = new BookDTO();
		bookDTO.setIssueedTo(studentId);
		ResponseEntity<BookDTO> exchange = restTemplate.exchange(
				url + "/book/issuebook?bookId={bookId}&studentId={studentId}", HttpMethod.PUT, null, BookDTO.class,
				bookId, studentId);
		StudentDTO convertToDTO = student.convertToDTO(student);
		convertToDTO.setBookDTO(exchange.getBody());
		return convertToDTO;
	}

	public StudentDTO issuebookfallback(int bookId, int studentId, Throwable throwable) {
		System.out.println("fallback");
		return new StudentDTO();
	}

	public Student deleteStudentById(int studentId) {
		Optional<Student> optionalStudent = studrepo.findById(studentId);
		Student student = optionalStudent.orElseThrow(() -> new StudentAPIException("Student does not exist"));
		studrepo.deleteById(studentId);
		return student;
	}

	@Transactional
	public StudentDTO returnBook(int bookId, int studentId) {
		if (bookId == 0 && studentId == 0) {
			throw new StudentAPIException("Either book id or student id is null");
		}
		Optional<Student> optionalStudent = studrepo.findById(studentId);
		Student student = optionalStudent.orElseThrow(() -> new StudentAPIException("Student does not exist"));
		BookDTO bookDTO = new BookDTO();
		bookDTO.setIssueedTo(studentId);
		ResponseEntity<BookDTO> exchange = restTemplate.exchange(
				url + "/book/returnbook?bookId={bookId}&studentId={studentId}", HttpMethod.PUT, null, BookDTO.class,
				bookId, studentId);
		StudentDTO convertToDTO = student.convertToDTO(student);
		convertToDTO.setBookDTO(exchange.getBody());
		return convertToDTO;
	}
}
