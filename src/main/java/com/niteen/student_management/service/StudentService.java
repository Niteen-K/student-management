package com.niteen.student_management.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.niteen.student_management.repository.StudentRepository;
import com.niteen.student_management.model.Student;

@Service
public class StudentService {
    
    @Autowired
    private StudentRepository studentRepository;

    public Student createStudent(Student student){
        if(studentRepository.existsByEmail(student.getEmail())){
            throw new RuntimeException("Email Alredy exists" + student.getEmail());
        }

        return studentRepository.save(student);
    }

    public List<Student> getAllStudents(){
        return studentRepository.findAll();
    }

    public Student getStudentByID(Long id){
        return studentRepository.findById(id).orElseThrow(()-> new RuntimeException("Student not found by ID"+ id));
    }

    public Student getStudentByEmail(String email){
        return studentRepository.findByEmail(email).orElseThrow(()-> new RuntimeException("STudnet not found by Email" + email));
    }

    public List<Student> getStudentByDepartment(String department){
        return studentRepository.findByDepartment(department);
    }

    public Student updateStudent(Long id, Student studentDetails) {
        Student student = getStudentByID(id);
        
        // Check if new email already exists (and it's not the current student's email)
        if (studentDetails.getEmail() != null && 
            !studentDetails.getEmail().equals(student.getEmail()) &&
            studentRepository.existsByEmail(studentDetails.getEmail())) {
            throw new RuntimeException("Email already exists: " + studentDetails.getEmail());
        }
        
        // Update fields if they are provided
        if (studentDetails.getFirstName() != null) {
            student.setFirstName(studentDetails.getFirstName());
        }
        if (studentDetails.getLastName() != null) {
            student.setLastName(studentDetails.getLastName());
        }
        if (studentDetails.getEmail() != null) {
            student.setEmail(studentDetails.getEmail());
        }
        if (studentDetails.getDepartment() != null) {
            student.setDepartment(studentDetails.getDepartment());
        }
        if (studentDetails.getAge() != null) {
            student.setAge(studentDetails.getAge());
        }
        
        return studentRepository.save(student);
    }
    
    // DELETE - Delete student
    public void deleteStudent(Long id) {
        Student student = getStudentByID(id);
        studentRepository.delete(student);
    }
    
    // DELETE - Delete all students
    public void deleteAllStudents() {
        studentRepository.deleteAll();
    }

}
