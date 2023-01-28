package com.example.student.dto;

import com.example.entity.Student;

public class StudentDTO {

	private int studentId;
	private String name;
	private String emailId;
	private int phoneNumber;
	private BookDTO bookDTO;
	
	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public int getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(int phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public BookDTO getBookDTO() {
		return bookDTO;
	}
	public void setBookDTO(BookDTO bookDTO) {
		this.bookDTO = bookDTO;
	}

	public Student convertToEntity() {
		Student student = new Student();
		student.setStudentId(this.getStudentId());
		student.setName(this.getName());
		student.setEmailId(this.getEmailId());
		student.setPhoneNumber(this.getPhoneNumber());
		return student;
	}
}
