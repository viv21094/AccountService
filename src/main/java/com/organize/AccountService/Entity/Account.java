package com.organize.AccountService.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter@Setter@ToString@AllArgsConstructor@NoArgsConstructor
public class Account extends BaseEntity{


	private Long customerId;
	
	@Id
	private Long accountNumber;
	
	private String accountType;
	
	private String branchAddress;

}
