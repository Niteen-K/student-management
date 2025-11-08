package com.niteen.student_management.repository;

import com.niteen.student_management.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    
    // Custom query methods
    Optional<Student> findByEmail(String email);
    List<Student> findByDepartment(String department);
    List<Student> findByAgeGreaterThan(Integer age);
    boolean existsByEmail(String email);
    Student save(String email);
}

