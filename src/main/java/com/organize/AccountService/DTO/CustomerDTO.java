package com.organize.AccountService.DTO;

import java.time.LocalDateTime;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CustomerDTO {
	private Long CustomerId;
	@NotBlank(message = "Name cannot be blank")
	@Size(min = 1, max = 10)
	private String name;
	@Email(message = "Email should be in email format")
	private String email;
	@NotBlank(message = "Mobile Number cannot be blank")
	@Pattern(regexp = "(^[0-9]{10})", message = "Mobile number must be 10 digits")
	private String mobileNumber;

	private AccountDTO account;

	private String createdBy;

	private LocalDateTime createdAt;
	
	private String updatedBy;
	
	private LocalDateTime updatedAt;


}
