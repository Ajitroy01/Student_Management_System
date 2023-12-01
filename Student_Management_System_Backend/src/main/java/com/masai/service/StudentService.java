package com.masai.service;

import java.util.List;

import com.masai.entity.Student;

public interface StudentService {

	Student createStudent(Student student);
	Student updateStudent(int studentId, Student student);
	String deleteStudent(int studentId);
	List<Student> getAllStudents();
	Student getStudentById(int studentId);
}
