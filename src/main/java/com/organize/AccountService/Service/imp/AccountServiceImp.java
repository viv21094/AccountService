package com.organize.AccountService.Service.imp;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.organize.AccountService.Constants.AccountConstants;
import com.organize.AccountService.DTO.AccountDTO;
import com.organize.AccountService.DTO.CustomerDTO;
import com.organize.AccountService.Entity.Account;
import com.organize.AccountService.Entity.Customer;
import com.organize.AccountService.Exception.AccountExistException;
import com.organize.AccountService.Exception.ResourceNotFoundException;
import com.organize.AccountService.Repository.AccountRepository;
import com.organize.AccountService.Repository.CustomerRepository;
import com.organize.AccountService.Service.IAccountService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AccountServiceImp implements IAccountService {

	private AccountRepository accountRepository;
	private CustomerRepository customerRepository;
	private ObjectMapper objectMapper;

	@Override
	public void createAccount(CustomerDTO customerDTO) {
		// TODO Auto-generated method stub
		Customer customer = objectMapper.convertValue(customerDTO, Customer.class);
		Optional<Customer> existCustomer = customerRepository.findByMobileNumber(customer.getMobileNumber());
		if (existCustomer.isPresent()) {
			throw new AccountExistException(
					"Customer mobile number already exist. We were not able to creat an account for you."
							+ customerDTO.getMobileNumber());
		}
		customer.setCreatedBy("Vivek");
		customer.setCreatedAt(LocalDateTime.now());
		Customer newCustomer = customerRepository.save(customer);
		
		Account newAccount2 = accountRepository.save(createNewAccount(newCustomer));
	}
	
	public Account createNewAccount(Customer customer) {
		Account newAccount = new Account();
		newAccount.setCreatedBy("Vivek");
		newAccount.setCreatedAt(LocalDateTime.now());
		newAccount.setCustomerId(customer.getCustomerId());
		long randomAccNumber = 1000000000L + new Random().nextInt(900000000);
		newAccount.setAccountNumber(randomAccNumber);
		newAccount.setAccountType(AccountConstants.SAVINGS);
		newAccount.setBranchAddress(AccountConstants.ADDRESS);
		return newAccount;
	}

	@Override
	public CustomerDTO getAccount(String mobileNumber) {
		Customer customer =customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
				() -> new ResourceNotFoundException("Customer", "Mobile Number", mobileNumber));
		Account account= accountRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
				()-> new ResourceNotFoundException("Accounts", "MobileNumber", mobileNumber));
		
		AccountDTO accountDTO = objectMapper.convertValue(account, AccountDTO.class);
		CustomerDTO customerDTO = objectMapper.convertValue(customer,CustomerDTO.class);
		customerDTO.setAccount(accountDTO);
		return customerDTO;
	}
	
	/* Updating account data and customer data 
	 * Takes CustomerDTO as input 
	 * returns a boolean value if the transaction is successful
	 *
	 */
	@Override
	public CustomerDTO updateAccount(CustomerDTO customerDTO) {
		Account account = accountRepository.findById(customerDTO.getAccount().getAccountNumber()).orElseThrow(
				()-> new ResourceNotFoundException("Account", "Account Number", customerDTO.getAccount().getAccountNumber().toString()));
		account = accountRepository.save(objectMapper.convertValue(customerDTO.getAccount(), Account.class));
		Customer customer =customerRepository.findByMobileNumber(customerDTO.getMobileNumber()).orElseThrow(
				()-> new ResourceNotFoundException("Customer", "Mobile Number", customerDTO.getAccount().getAccountNumber().toString()));
		customer=customerRepository.save(objectMapper.convertValue(customerDTO, Customer.class));
		AccountDTO accountDTO = objectMapper.convertValue(account, AccountDTO.class);
		CustomerDTO customerDTO2 = objectMapper.convertValue(customer, CustomerDTO.class);
		customerDTO2.setAccount(accountDTO);
		return customerDTO2;
	}
	/*
	 * This Method is used to delete account and customer
	 * Takes mobile number of the customer as input
	 * checkes if mobile number is valid and associated with a account
	 * Deletes with the help of transaction
	 * Returns true if no error 
	 */
	@Override
	public boolean deleteCustomer(String mobileNumber) {
		Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
				() -> new ResourceNotFoundException("Customer", "Customer mobile Number", mobileNumber));
		accountRepository.deleteByCustomerId(customer.getCustomerId());
		customerRepository.deleteById(customer.getCustomerId());
		return true;
	}
	

}
