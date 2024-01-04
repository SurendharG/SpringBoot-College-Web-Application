package com.simpleform0.service;

import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.simpleform0.Repositery.ApplicationsRepository;
import com.simpleform0.Repositery.StudentRepository;
import com.simpleform0.Repositery.UserssRepository;
import com.simpleform0.model.Applications;
import com.simpleform0.model.Student;
import com.simpleform0.model.UserssModel;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ApplicationsService {
	private final StudentRepository studentRepository;
	private final UserssRepository userssRepository;
	private final ApplicationsRepository applyRepository;
	public ApplicationsService(StudentRepository studentRepository, UserssRepository userssRepository,ApplicationsRepository applyRepository) {
        this.studentRepository = studentRepository;
        this.userssRepository = userssRepository;
        this.applyRepository = applyRepository;
    }
	public Applications applyform(Applications a) {
		try{return applyRepository.save(a);}
		catch(EntityNotFoundException e) {
			return null;
		}
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
	public List<Applications> getAllApplication(){
		try {
			return applyRepository.findAll();}
		catch(EntityNotFoundException e) {
			return null;
		}
	}
	public Applications getApplicationById(int id) {
		return applyRepository.getById(id);
	}
	public  int save(Student a) {
		try{ studentRepository.addstudent(a.getRegno(),a.getAddress(),a.getContact_no(),a.getCourse(),a.getDob(),a.getGender(),a.getName(),a.getPaid_fees(),a.getPassword(),a.getPending_fees(),a.getTotal_fees());}
		catch(DataIntegrityViolationException e) {
			return 2;
		}
		return 1;
	}
	public Student CheckRegnoif(Long regno) {
		return studentRepository.findById(regno).orElse(null);
	}
	public Student getAlldetails(Long rn) {
		return studentRepository.getById(rn);
	}
	public Student saving(Student s) {
		return studentRepository.save(s);
	}
	public boolean deleteapplication(Integer aid) {
		try {
			applyRepository.deleteById(aid);
			return true;
		}
		catch(DataIntegrityViolationException e) {
			return false;
		}
	}
}

