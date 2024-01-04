package com.simpleform0.Repositery;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.simpleform0.model.Reviews;

public interface ReviewsRepository extends JpaRepository<Reviews,Integer>{

	@Query(value= " select count(rino) from reviews;", nativeQuery=true)
	int getreviewscount();
	@Query(value= "  select avg(star) from reviews;", nativeQuery=true)
	double getreviewsAverage();

	
}
