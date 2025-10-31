package com.StudentsManagementSystem.serviceImpl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.StudentsManagementSystem.entity.Student;
import com.StudentsManagementSystem.repository.StudentRepository;
import com.StudentsManagementSystem.service.StudentService;

@Service
public class ServiceImpl implements StudentService {

    @Autowired
    private StudentRepository repo;

    @Override
    public List<Student> getAllStudent() {
        return repo.findAll();
    }

    @Override
    public Student saveStudent(Student student) {
        return repo.save(student);
    }

	@Override
	public Student getById(int id) {
		
		return repo.findById(id).orElse(null); 
	}

	@Override
	public void deleteById(int id) {
	    repo.deleteById(id);
	}
	
}

