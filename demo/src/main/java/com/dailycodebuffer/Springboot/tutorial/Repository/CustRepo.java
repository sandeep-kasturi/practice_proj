package com.dailycodebuffer.Springboot.tutorial.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.dailycodebuffer.Springboot.tutorial.entity.Customer;

@Repository
public interface CustRepo extends JpaRepository<Customer, Integer>{

	@Query("select c from Customer c where c.email = '%?1' ")
	public List<Customer> findByEmail(String pattern);
}
