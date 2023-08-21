package com.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.user.model.User;
import com.user.service.UserService;



@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*")
public class UserController {

	 @Autowired
	    private UserService userService;

	    @PostMapping("/register")
	    public String registerUser(@RequestBody User user) {
	        return userService.registerUser(user);
	    }

	    @PostMapping("/login")
	    public String loginUser(@RequestParam String email, @RequestParam String password) {
	        return userService.loginUser(email, password);
	    }
	
	
}
