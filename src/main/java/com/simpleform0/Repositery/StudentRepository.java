package com.simpleform0.Repositery;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.simpleform0.model.Student;
public interface StudentRepository extends JpaRepository<Student,Long>{
	Optional<Student> findByRegnoAndPassword(Long regno,String password);
	
	@Modifying
	@Transactional
	@Query(value= "insert into student_details values(?1, ?2,?3,?4,?5,?6,?7,?8,?9,?10,?11);", nativeQuery=true)
	int addstudent(Long regno,String address,Long contact_no,String course,Date dob,String gender,String name,BigDecimal paid_fees,String password,BigDecimal pending_fees,BigDecimal total_fees);
	
	@Modifying
	@Transactional
	@Query(value=" update student_details set paid_fees=paid_fees+?1,pending_fees=pending_fees-?1 WHERE regno=?3 && name=?2",nativeQuery=true)
	int updatefees(BigDecimal amount,String name,Long regno);







}
