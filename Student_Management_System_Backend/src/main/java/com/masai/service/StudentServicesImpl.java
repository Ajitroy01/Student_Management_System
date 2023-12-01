package com.masai.service;

import com.masai.entity.Student;
import com.masai.exception.StudentException;
import com.masai.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class StudentServicesImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepo;

    @Override
    public Student createStudent(Student student) {
    	student.setEnrollmentDate(LocalDate.now());
        return studentRepo.save(student);
    }

    @Override
    public Student updateStudent(int studentId, Student updatedStudent) {
        Optional<Student> existingStudentOptional = studentRepo.findById(studentId);

        if (existingStudentOptional.isPresent()) {
            Student existingStudent = existingStudentOptional.get();

            existingStudent.setFirstName(updatedStudent.getFirstName());
            existingStudent.setLastName(updatedStudent.getLastName());
            existingStudent.setGender(updatedStudent.getGender());
            existingStudent.setDateOfBirth(updatedStudent.getDateOfBirth());
            existingStudent.setAddress(updatedStudent.getAddress());

            return studentRepo.save(existingStudent);
        } else {
            throw new StudentException("Student not found with ID: " + studentId);
        }
    }


    @Override
    public String deleteStudent(int studentId) {
        // Check if the student with the given ID exists
        Optional<Student> existingStudent = studentRepo.findById(studentId);

        if (existingStudent.isPresent()) {
            studentRepo.deleteById(studentId);
            return "Student deleted successfully";
        } else {
            throw new StudentException("Student not found with ID: " + studentId);
        }
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepo.findAll();
    }

    @Override
    public Student getStudentById(int studentId) {
        Optional<Student> student = studentRepo.findById(studentId);

        if (student.isPresent()) {
            return student.get();
        } else {
            throw new StudentException("Student not found with ID: " + studentId);
        }
    }
}
