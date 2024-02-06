package com.simpleform0.service;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.util.List;

import org.springframework.stereotype.Service;

import com.simpleform0.Repositery.AccountRepository;
import com.simpleform0.Repositery.ApplicationsRepository;
import com.simpleform0.Repositery.StudentRepository;
import com.simpleform0.Repositery.UserssRepository;
import com.simpleform0.model.AccountHistory;
import com.simpleform0.model.Student;
import com.simpleform0.model.UserssModel;

import jakarta.persistence.EntityNotFoundException;

@Service
public class AccountService {

	private final StudentRepository studentRepository;
	private final UserssRepository userssRepository;
	private final AccountRepository accRepository;
	public AccountService(StudentRepository studentRepository, UserssRepository userssRepository,AccountRepository accRepository) {
        this.studentRepository = studentRepository;
        this.userssRepository = userssRepository;
        this.accRepository = accRepository;
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
		else if(a.equalsIgnoreCase("admin")) {
			return 1;
		}
		else {
			return 0;
		}
	}
	public Student CheckRegnoif(Long rn) {
		return studentRepository.findById(rn).orElse(null);
	}
	public AccountHistory save(AccountHistory ach) {
		return accRepository.save(ach);
	}
	public int updatefees(BigDecimal amount,String name,Long regno) {
		try {
			studentRepository.updatefees(amount,name,regno);
			return 1;
		}
		catch(EntityNotFoundException e) {
			return 2;
		}
	}
	public List<AccountHistory> GetHistory(Date a,java.util.Date dates){
		return accRepository.getHistory(a,dates);
	}
	
}
