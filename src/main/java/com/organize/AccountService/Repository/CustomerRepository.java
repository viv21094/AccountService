package com.organize.AccountService.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.organize.AccountService.Entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>{

	public Optional<Customer> findByMobileNumber(String mobileNumber);
	
}
