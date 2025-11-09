package com.niteen.student_management.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "students")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message= "First name is required")
    @Size(min=2 ,max=50, message = "Name should be between length 2 to 50")
    @Column(nullable = false)
    private String firstName;
    
    @NotBlank(message = "Last name is required")
    @Size(min=2 ,max=50, message = "Name should be between length 2 to 50")
    @Column(nullable = false)
    private String lastName;
    
    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    @Column(nullable = false, unique = true)
    private String email;
    
    @NotBlank(message = "Department is required")
    private String department;

    @NotNull(message = "Age is required")
    @Min(value = 16)
    private Integer age;
    
    // Constructor without ID (for creating new students)
    public Student(String firstName, String lastName, String email, String department, Integer age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.department = department;
        this.age = age;
    }
}