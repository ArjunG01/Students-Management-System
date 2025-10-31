package com.StudentsManagementSystem.service;

import java.util.List;
import com.StudentsManagementSystem.entity.Student;

public interface StudentService {
    List<Student> getAllStudent();
    
    public Student saveStudent(Student student);
    
    
    public Student getById(int id);

    void deleteById(int id);


}
 