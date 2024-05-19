package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
// api layer
@RestController
/*
Indicates that this class serves endpoints. Hence when the program is ran, the function with
@GetMapping annotation will be automatically displayed on localhost
 */
@RequestMapping(path = "api/v1/student")
// info is displayed on localhost:8080/api/v1/student
public class StudentController {
    private final StudentService studentService;

    @Autowired
    /* Instantiates the StudentController object for us. The reason why you do not do `new StudentController()`
        is because this introduces dependencies. Spring Framework uses Dependency Injection Containers to create the object for you, and injects this dependency into the class that needs it, using the @Autowired and @Component annotations
      */
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    // to make this method serve as a restful endpoint
    public List<Student> getStudents() {
        return studentService.getStudents();
    }

    @PostMapping
    public void registerNewStudent(@RequestBody Student student) {
        // take a RequestBody and map it to a Student, and pass it as a parameter
        studentService.addNewStudent(student);
    }

    @DeleteMapping(path = "{studentId}")
    public void deleteStudent(@PathVariable("studentId") Long id) {
        studentService.deleteStudent(id);
    }

    @PutMapping(path = "{studentId}")
    public void updateStudent(
            @PathVariable("studentId") Long id,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email
    ) {
        studentService.updateStudent(id, name, email);
    }
}
