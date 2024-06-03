package com.organize.AccountService.DTO;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class CustomerDTO {
	
	private Long CustomerId;
	
	private String name;
	
	private String email;
	
	private String mobileNumber;
	
	private AccountDTO account;
	
	private String createdBy;
	
	private LocalDateTime createdAt;
	
}
