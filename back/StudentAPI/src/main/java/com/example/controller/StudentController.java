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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Student;
import com.example.service.StudentService;
import com.example.student.dto.StudentDTO;

@RestController
@CrossOrigin
public class StudentController {

	@Autowired
	StudentService studservice;

	@PostMapping("/student")
	public ResponseEntity<Student> addStudent(@Valid @RequestBody StudentDTO studentDTO) {
		Student stud = studservice.addStudentToDB(studentDTO);
		return new ResponseEntity<Student>(stud, HttpStatus.CREATED);
	}

	@GetMapping("/student/getstudent/{studentId}")
	public Student getStudent(@PathVariable int studentId) {
		return studservice.getStudentById(studentId);
	}

	@GetMapping("/student/issueBook")
	public ResponseEntity<StudentDTO> issueBook(int bookId, int studentId) {
		StudentDTO issueBook = studservice.issueBookToStudent(bookId, studentId);
		return new ResponseEntity<StudentDTO>(issueBook, HttpStatus.CREATED);
	}

	@DeleteMapping("/student/deletestudent/{studentId}")
	public Student deleteStudentById(@PathVariable int studentId) {
		return studservice.deleteStudentById(studentId);
	}

	@GetMapping("/student/returnBook")
	public ResponseEntity<StudentDTO> returnBook(int bookId, int studentId) {
		StudentDTO returnBook = studservice.returnBook(bookId, studentId);
		return new ResponseEntity<StudentDTO>(returnBook, HttpStatus.CREATED);
	}
}
