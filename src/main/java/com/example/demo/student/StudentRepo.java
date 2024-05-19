package com.example.demo.student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepo extends JpaRepository<Student, Long> {
    // @Query("SELECT s FROM Student s WHERE s.email = ?1")
    /*
        JpaRepository actually automatically creates the query above by inferring from the method name,
        thus you don't have to include the query statement above
     */
    Optional<Student> getStudentByEmail(String email);
}
