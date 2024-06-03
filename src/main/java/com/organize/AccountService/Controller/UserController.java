package com.organize.AccountService.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
	@GetMapping("/get")
	public String getFirst() {
		return "This is my first project";
	}

}
