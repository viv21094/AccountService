package com.organize.AccountService.Controller;

import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.organize.AccountService.Constants.AccountConstants;
import com.organize.AccountService.DTO.CustomerDTO;
import com.organize.AccountService.DTO.ResponseDTO;
import com.organize.AccountService.Service.IAccountService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "/account", produces = { MediaType.APPLICATION_JSON_VALUE }, consumes = {
		MediaType.APPLICATION_JSON_VALUE })
@AllArgsConstructor
@Validated
public class AccountController {

	private IAccountService iAccountService;

	@PostMapping("/create")
	public ResponseEntity<ResponseDTO> createAccount(@RequestBody @Valid CustomerDTO customerDTO) {
		iAccountService.createAccount(customerDTO);
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(new ResponseDTO(AccountConstants.STATUS_201, AccountConstants.MESSAGE_201));
	}

	@PostMapping("/getaccount")
	public ResponseEntity<CustomerDTO> getbyMobileNumber(@RequestBody String mobileNumber) {
		JSONObject data = new JSONObject(mobileNumber);
		@Valid
		String mob = data.get("mobileNumber").toString();
		CustomerDTO customerDTO = iAccountService.getAccount(mob);
		return ResponseEntity.status(HttpStatus.OK).body(customerDTO);
	}

	@PostMapping("/updateaccount")
	public ResponseEntity<CustomerDTO> updateAccount(@RequestBody @Valid CustomerDTO customerDTO) {
		CustomerDTO customerDTOUpdated = iAccountService.updateAccount(customerDTO);
		return ResponseEntity.status(HttpStatus.OK).body(customerDTOUpdated);
	}

	@PostMapping("/deleteaccount")
	public ResponseEntity<ResponseDTO> deleteAccount(@RequestBody String mobileNumber) {
		JSONObject data = new JSONObject(mobileNumber);
		@Valid
		String mob = data.get("mobileNumber").toString();
		boolean isDeleted = false;
		isDeleted = iAccountService.deleteCustomer(mob);
		if (isDeleted) {
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(AccountConstants.MESSAGE_200,
					"Account deleted"));
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseDTO(
					AccountConstants.MESSAGE_500, AccountConstants.MESSAGE_500));
		}

	}
}
