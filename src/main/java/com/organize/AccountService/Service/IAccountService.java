package com.organize.AccountService.Service;

import com.organize.AccountService.DTO.CustomerDTO;
import com.organize.AccountService.Entity.Account;

public interface IAccountService {
	
	void createAccount(CustomerDTO customerDTO);
	
	CustomerDTO getAccount(String mobileNumber);
	
	CustomerDTO updateAccount(CustomerDTO customerDTO);
	
	boolean deleteCustomer(String mobileNumber);

}
