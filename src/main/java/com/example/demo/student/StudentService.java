package com.example.demo.student;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// business logic layer
@Service // indicates that this class is a service class, i.e. has to be instantiated automatically
public class StudentService {
    private final StudentRepo studentRepo;

    @Autowired
    public StudentService(StudentRepo studentRepo) {
        this.studentRepo = studentRepo;
    }

    public List<Student> getStudents() {
        return studentRepo.findAll();
    }

    public void addNewStudent(Student student) {
        Optional<Student> studentOptional = studentRepo.getStudentByEmail(student.getEmail());
        if (studentOptional.isPresent()) {
            throw new IllegalStateException("the student you are trying to add already exists");
        }
        studentRepo.save(student);
    }

    public void deleteStudent(Long id) {
        boolean exists = studentRepo.existsById(id);
        if (!exists) {
            throw new IllegalStateException("Student with id " + id + " does not exist");
        }
        studentRepo.deleteById(id);
    }

    @Transactional
    /*
    Makes this method a transaction, similar to transaction in SQL. Either fully commit or rollback
     */
    public void updateStudent(Long id, String name, String email) {
        // first find out if student exists
        Student student = studentRepo.findById(id)
                .orElseThrow(() -> new IllegalStateException(
                        "The student you are trying to update does not exist"
                ));
        // check if the name is valid
        if (name != null &&
                name.length() > 0 &&
                !name.equals(student.getName())) {
            student.setName(name);
        }
        // check if email is valid
        if (email != null &&
                email.length() > 0 &&
                !email.equals(student.getEmail())) {
            // check that this email is not already used
            Optional<Student> studentOptional = studentRepo.getStudentByEmail(email);
            if (studentOptional.isPresent()) {
                throw new IllegalStateException(
                        "This email you are trying to set is already possessed by another user"
                );
            }
            student.setEmail(email);
        }
    }
}
