package com.tpe.service;

import com.tpe.domain.Student;
import com.tpe.exception.ConflictException;
import com.tpe.exception.ResourceNotFoundException;
import com.tpe.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    //!!! Get ALL STUDENTS
    public List<Student> getAll() {

       return studentRepository.findAll();

    }

    // !!! Create new Student
    public void createStudent(Student student) {

        //!!! email ile unique kontrolu
        if(studentRepository.existsByEmail(student.getEmail())){
            throw new ConflictException("Email is already exist");
        }
        studentRepository.save(student);
    }

    //!!! Not: getStudentById RequestParam *******
    public Student findStudent(Long id) {

        return studentRepository.findById(id).
                orElseThrow(()->new ResourceNotFoundException("Student not found with id: "+id));

    }

    // Not deleteStudentById
    public void deleteStudent(Long id) {
        //Acaba yukarıda verilen id li student var mı?
        Student student = findStudent(id);
        studentRepository.delete(student);

    }
}
