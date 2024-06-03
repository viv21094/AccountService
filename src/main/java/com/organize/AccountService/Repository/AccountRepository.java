package com.organize.AccountService.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import com.organize.AccountService.Entity.Account;

import jakarta.transaction.Transactional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long>{

	Optional<Account> findByCustomerId(Long customerId);
	
	@Transactional
	@Modifying
	void deleteByCustomerId(Long customerId);

}
