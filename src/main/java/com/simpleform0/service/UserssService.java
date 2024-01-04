package com.simpleform0.service;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.simpleform0.Repositery.ReviewsRepository;
import com.simpleform0.Repositery.UserssRepository;
import com.simpleform0.model.Reviews;
import com.simpleform0.model.UserssModel;

import jakarta.persistence.EntityNotFoundException;
@Service
public class UserssService {
	private final UserssRepository userssRepository;
	private final ReviewsRepository reviewsRepository;
	public UserssService(UserssRepository usersssRepository,ReviewsRepository reviewsRepository) {
		this.userssRepository=usersssRepository;
		this.reviewsRepository=reviewsRepository;
	}
	public UserssModel authenticate(Integer personalid,String password) {
		return userssRepository.findByPersonalidAndPassword(personalid, password).orElse(null);
	}
	public UserssModel AddingUser(Integer personalid,String username,String password,String access) {
		if(personalid != null && username !=null && password !=null) {
			UserssModel userssModel= new UserssModel();
			userssModel.setPersonalid(personalid);
			userssModel.setUsername(username);
			userssModel.setPassword(password);
			userssModel.setAccess(access);
			try {
                return userssRepository.save(userssModel);
            } catch (DataIntegrityViolationException e) {
                e.printStackTrace();
                return null;
            }			
		}
		return null;
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
		else if(a.equals(null)) {
			return 0;
		}
		else {
			return 0;
		}
	}
	public Optional<UserssModel> Askid(Integer personalid) {
		if(personalid != null) {
			Optional<UserssModel> optionalEntity = userssRepository.findByPersonalId(personalid);
	        if (optionalEntity.isPresent()) {
	            return Optional.of(optionalEntity.get());
	            } else {
	            	return null;
	            }
		}
		return null;
	}
	public UserssModel Edit(Integer personalid) {
			UserssModel d = userssRepository.forEdit(personalid);	
			return d;}
	public boolean Update(Integer pid,String uname,String pword,String access) {
		String un=uname;
		String pw=pword;
		String ac=access;
		Integer peid=pid;
		int a=userssRepository.UpdateUser(un,pw,ac,peid);
		if(a>=1) {
			return true;
		}
		else 
			return false;
	}
	public boolean delete(Integer usid) {
		int a=userssRepository.deleteByIdown(usid);
		if(a>=1) {
			return true;
		}
		else 
			return false;
	}
	public List<UserssModel> getAlluser() {
		try {
		return userssRepository.findAll();}
		catch(EntityNotFoundException e) {
			return null;
		}
	}
	public int getreviewscount() {
		return reviewsRepository.getreviewscount();
	}
	public double getreviewsaverage() {
		return reviewsRepository.getreviewsAverage();
	}
	public List<Reviews> getallReviews(){
		try {
			return reviewsRepository.findAll();
		}catch(EntityNotFoundException e) {
			return null;
		}
	}
	public int saveReview(Reviews r) {
		try {
			reviewsRepository.save(r);
			return 1;
		}
		catch(DataIntegrityViolationException e) {
			return 2;
		}
		catch(EntityNotFoundException e) {
			return 3;
		}
		}
	






}

	
	
	
	
	