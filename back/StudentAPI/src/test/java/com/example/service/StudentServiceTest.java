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

import com.example.entity.Student;
import com.example.exception.StudentAPIException;
import com.example.repository.StudentRepository;
import com.example.student.dto.StudentDTO;

@SpringBootTest
public class StudentServiceTest {

	@InjectMocks
	StudentService studservice;
	@Mock
	StudentRepository studrepo;

	@Test
	void testAddStudentWithStudentAlreadyAvailable() {
		when(studrepo.findById(ArgumentMatchers.anyInt())).thenReturn(Optional.of(new Student()));
		assertThrows(StudentAPIException.class, () -> studservice.addStudentToDB(new StudentDTO()));
	}
	
	@Test
	void testaddStudentToDBWithSuccess() {
		when(studrepo.findById(ArgumentMatchers.anyInt())).thenReturn(Optional.empty());
		when(studrepo.save(ArgumentMatchers.any())).thenReturn(new Student());
		Student addBookInDB = studservice.addStudentToDB(new StudentDTO());
		assertNotNull(addBookInDB);
	}
	
	@Test
	void testaddStudentWithSuccessWithStudentId() {
		when(studrepo.findById(ArgumentMatchers.anyInt())).thenReturn(Optional.empty());
		Student e = new Student();
		e.setStudentId(11);
		when(studrepo.save(ArgumentMatchers.any())).thenReturn(e);
		StudentDTO studentDTO = new StudentDTO();
		studentDTO.setStudentId(11);
		Student addStudentInDB= studservice.addStudentToDB(studentDTO);
		assertEquals(studentDTO.getStudentId(), addStudentInDB.getStudentId());
	}

	@Test
	void testGetStudentWithStudentNotInDBScenario() {
		when(studrepo.findById(ArgumentMatchers.anyInt())).thenReturn(Optional.empty());
		assertThrows(StudentAPIException.class, () -> studservice.getStudentById(123));
	}

	@Test
	void testIssueBookinStudentAPIStudNotInDbScenario() {
		when(studrepo.findById(ArgumentMatchers.anyInt())).thenReturn(Optional.empty());
		assertThrows(StudentAPIException.class, () -> studservice.issueBookToStudent(11, ArgumentMatchers.anyInt()));
	}
	
	@Test
	void testIssueBookinStudentAPIStudentWithZeroBookIdStudentId() {
		assertThrows(StudentAPIException.class, () -> studservice.issueBookToStudent(0, 0));
	}

	@Test
	void testReturnBookinStudentAPIStudNotInDbScenario() {
		when(studrepo.findById(ArgumentMatchers.anyInt())).thenReturn(Optional.empty());
		assertThrows(StudentAPIException.class, () -> studservice.returnBook(11, ArgumentMatchers.anyInt()));
	}

	@Test
	void testReturnBookinStudentAPIStudWithZeroBookStudentId() {
		assertThrows(StudentAPIException.class, () -> studservice.returnBook(0, 0));
	}
	
	@Test
	void testDeleteStudentinStudentAPIDeleteStudentById() {
		when(studrepo.findById(ArgumentMatchers.anyInt())).thenReturn(Optional.empty());
		assertThrows(StudentAPIException.class, () -> studservice.deleteStudentById(123));
	}

}