package com.simpleform0.Repositery;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.simpleform0.model.AccountHistory;
public interface AccountRepository extends JpaRepository<AccountHistory,Integer>{

	
	@Modifying
	@Transactional
	@Query(value="select * from accountmaintenance where date>=?1 AND date<=?2",nativeQuery=true)
	List<AccountHistory> getHistory(Date a,java.util.Date dates);
	
}
