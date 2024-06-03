package com.organize.AccountService.DTO;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class AccountDTO {

	private Long customerId;
	
	private Long accountNumber;
	
	private String accountType;
	
	private String branchAddress;
	
	private String createdBy;
	
	private LocalDateTime createdAt;
	
}
