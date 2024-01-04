package com.simpleform0.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.simpleform0.Repositery.StudentRepository;
import com.simpleform0.Repositery.UserssRepository;
import com.simpleform0.model.Student;
import com.simpleform0.model.UserssModel;
import jakarta.persistence.EntityNotFoundException;

@Service
public class StudentService {
	private final StudentRepository studentRepository;
	private final UserssRepository userssRepository;
	public StudentService(StudentRepository studentRepository, UserssRepository userssRepository) {
        this.studentRepository = studentRepository;
        this.userssRepository = userssRepository;
    }

	public UserssModel authenticate(Integer personalid,String password) {
		return  userssRepository.findByPersonalidAndPassword(personalid, password).orElse(null);
	}
	public int accesscheck(Integer pid) {
		String a =userssRepository.findAccess(pid);
		if(a.equalsIgnoreCase("staff")) {
			return 2;
		}
		else if(a.equalsIgnoreCase("adm")) {
			return 3;
		}
		else if(a.equalsIgnoreCase("acc")) {
			return 4;
		}
//		else if(a.equals(null)) {
//			return 0;
//		}
		else {
			return 0;
		}
	}
	public Student CheckRegnoif(Long rn) {
		return studentRepository.findById(rn).orElse(null);
	}
	public Student save(Student s) {
		try {
		return studentRepository.save(s);}
		catch(EntityNotFoundException e) {
			return null;
		}
	}
	public <Optional>Student CheckRegnoiffordelete(Long rn) {
		return studentRepository.findById(rn).orElse(null);
	}
	public Student getAlldetails(Long n){
	return studentRepository.getById(n);
	}
	
	public String deleting(Long id) {
		try {
		 studentRepository.deleteById(id);;
		 return "Notnull";
		 }
		catch(EntityNotFoundException e) {
			return null;
		}
	}
	public List<Student> getAllstudent() {
		try {
		return studentRepository.findAll();}
		catch(EntityNotFoundException e) {
			return null;
		}
	}
	public Student authticatingstudent(Long n,String pa) {
			return studentRepository.findByRegnoAndPassword(n, pa).orElse(null);
	}

	
}